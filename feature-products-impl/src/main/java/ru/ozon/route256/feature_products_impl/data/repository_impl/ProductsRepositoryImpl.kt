package ru.ozon.route256.feature_products_impl.data.repository_impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_utils.NetUtil
import ru.ozon.route256.core_utils.requireValue
import ru.ozon.route256.feature_products_impl.data.mapper.toDomain
import ru.ozon.route256.feature_products_impl.data.mapper.toEntity
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ProductApi,
    private val cacheApi: CacheApi
) : ProductsRepository {

    override suspend fun getProducts(): List<ProductInListDomain> = withContext(Dispatchers.IO) {
        if (cacheApi.getCacheProductList() == null) {
            val responseList = NetUtil.get {
                apiService.getProductsInList()
            }
            cacheApi.updateCacheProductList(responseList.requireValue().map {
                it.toEntity()
            })
        }
        return@withContext cacheApi.getCacheProductList()?.map {
            it.toDomain()
        } ?: mutableListOf()
    }


    override suspend fun addCountView(id: String?) {
        withContext(Dispatchers.IO) {
            val cacheList = cacheApi.getCacheProductList()
            if (cacheList == null) {
                val list = NetUtil.get {
                    apiService.getProducts()
                }
                cacheApi.updateCacheProducts(list.requireValue().map {
                    it.toEntity()
                })
                val newCacheList = cacheApi.getCacheProductList()!!
                newCacheList.mapIndexed { _, productInListUI ->
                    if (productInListUI.guid == id) {
                        productInListUI.countView += 1
                        return@mapIndexed
                    }
                }
                cacheApi.updateCacheProductList(newCacheList)
            }
            else {
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
}