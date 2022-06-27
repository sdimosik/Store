package ru.ozon.route256.feature_products_impl.data.repository_impl

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.feature_products_impl.data.mapper.toDomain
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.data.worker.DetailProductsWorker
import ru.ozon.route256.feature_products_impl.data.worker.ProductListWorker
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ProductApi,
    private val cacheApi: CacheApi,
    private val context: Context
) : ProductsRepository {

    private val loadWorkManager by lazy(LazyThreadSafetyMode.NONE) {
        WorkManager.getInstance(context)
    }

    override suspend fun getProducts(): List<ProductInListDomain> = withContext(Dispatchers.IO) {
        return@withContext cacheApi.getCacheProductList()?.map {
            it.toDomain()
        } ?: mutableListOf()
    }


    override suspend fun addCountView(id: String?) {
        withContext(Dispatchers.IO) {
            val networkCacheList = cacheApi.getOnlyCacheProductList()
            val addedList = cacheApi.getAddCacheProductList()
            networkCacheList?.mapIndexed { _, productInListUI ->
                if (productInListUI.guid == id) {
                    productInListUI.countView += 1
                    return@mapIndexed
                }
            }
            addedList?.mapIndexed { _, productInListUI ->
                if (productInListUI.guid == id) {
                    productInListUI.countView += 1
                    return@mapIndexed
                }
            }
            networkCacheList?.let { cacheApi.updateCacheProductList(it) }
            addedList?.let { cacheApi.updateAddCacheProductList(it) }
        }
    }

    override fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>> {
        loadWorkManager.cancelAllWork()

        val inputData: Data = Data.Builder()
            .putBoolean("anyway", forceRefresh)
            .build()

        val productListRequest = OneTimeWorkRequest.Builder(ProductListWorker::class.java)
            .setInputData(inputData)
            .setConstraints(Constraints().apply {
                requiredNetworkType = NetworkType.CONNECTED
            })
            .build()

        val detailProductsRequest = OneTimeWorkRequest.Builder(DetailProductsWorker::class.java)
            .setInputData(inputData)
            .setConstraints(Constraints().apply {
                requiredNetworkType = NetworkType.CONNECTED
            })
            .build()

        loadWorkManager.beginWith(productListRequest)
            .then(detailProductsRequest)
            .enqueue()

        return mutableListOf(
            loadWorkManager.getWorkInfoByIdLiveData(productListRequest.id),
            loadWorkManager.getWorkInfoByIdLiveData(detailProductsRequest.id)
        )
    }
}