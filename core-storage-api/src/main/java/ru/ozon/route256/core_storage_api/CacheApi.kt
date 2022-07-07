package ru.ozon.route256.core_storage_api

import ru.ozon.route256.core_storage_api.model.InCartGuidEntity
import ru.ozon.route256.core_storage_api.model.InCartGuidListEntity
import ru.ozon.route256.core_storage_api.model.ProductEntity
import ru.ozon.route256.core_storage_api.model.ProductInListEntity

interface CacheApi {

    suspend fun updateCacheProductList(list: List<ProductInListEntity>)

    suspend fun getCacheProductList(): List<ProductInListEntity>?

    suspend fun getOnlyCacheProductList(): List<ProductInListEntity>?

    suspend fun updateCacheProducts(list: List<ProductEntity>)

    suspend fun getCacheProducts(): List<ProductEntity>?

    suspend fun updateAddCacheProductList(list: List<ProductInListEntity>)

    suspend fun getAddCacheProductList(): List<ProductInListEntity>?

    suspend fun updateAddCacheProducts(list: List<ProductEntity>)

    suspend fun getAddCacheProducts(): List<ProductEntity>?

    suspend fun addProduct(product: ProductEntity)

    suspend fun updateInCart(list: List<InCartGuidEntity>)

    suspend fun getInCart(): List<InCartGuidEntity>?
}