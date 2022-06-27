package ru.ozon.route256.feature_products_impl.data.worker

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_utils.NetUtil
import ru.ozon.route256.core_utils.requireValue
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.worker_impl.data.toEntity
import javax.inject.Inject

class ProductListWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    companion object {
        fun createRequest(forceRefresh: Boolean): OneTimeWorkRequest {
            val inputData: Data = Data.Builder()
                .putBoolean("anyway", forceRefresh)
                .build()

            return OneTimeWorkRequest.Builder(ProductListWorker::class.java)
                .setInputData(inputData)
                .setConstraints(Constraints().apply {
                    requiredNetworkType = NetworkType.CONNECTED
                })
                .build()
        }
    }

    @Inject
    lateinit var cacheApi: CacheApi

    @Inject
    lateinit var productApi: ProductApi

    override suspend fun doWork(): Result {
        ProductFeatureComponent.get()?.inject(this)
        return coroutineScope {
            try {
                val f = async(Dispatchers.IO) {
                    val forceRefresh = inputData.getBoolean("anyway", false)
                    if (cacheApi.getCacheProductList() == null || forceRefresh) {
                        val responseList = NetUtil.get {
                            productApi.getProductsInList()
                        }
                        cacheApi.updateCacheProductList(responseList.requireValue().map {
                            it.toEntity()
                        })
                    }
                }

                f.await()

                Result.success()
            } catch (exception: Exception) {
                Result.failure()
            }
        }
    }
}