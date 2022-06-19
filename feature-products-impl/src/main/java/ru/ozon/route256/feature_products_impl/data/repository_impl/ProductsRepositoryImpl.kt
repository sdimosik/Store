package ru.ozon.route256.feature_products_impl.data.repository_impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.feature_products_impl.data.mapper.toDomain
import ru.ozon.route256.feature_products_impl.data.mapper.toEntity
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ProductApi,
    private val cacheApi: CacheApi
) : ProductsRepository {

    companion object {
        // cache moment
        private var isFirstCacheList = true
    }

    override suspend fun getProducts(): List<ProductInListDomain> = withContext(Dispatchers.IO) {
        if (isFirstCacheList) {
            val response = apiService.getProductsInList()

//            if (!response.isSuccessful)
//                return@withContext cacheList
//
//            if (response.body() == null)
//                return@withContext cacheList

//            cacheApi.updateCacheProductList(response.map {
//                it.toEntity()
//            }.toMutableList())
//
//
//            isFirstCacheList = false
//            return@withContext cacheApi.getCacheProductList().map {
//                it.toDomain()
//            }
            return@withContext response.map {
                it.toDomain()
            }
        } else {
            return@withContext cacheApi.getCacheProductList().map {
                it.toDomain()
            }
        }
    }


    override suspend fun addCountView(id: String?) {
        withContext(Dispatchers.IO) {
            val cacheList = cacheApi.getCacheProductList()
            cacheList.mapIndexed { _, productInListUI ->
                if (productInListUI.guid == id) {
                    productInListUI.countView += 1
                    return@mapIndexed
                }
            }
            cacheApi.updateCacheProductList(cacheList)
        }
    }
}