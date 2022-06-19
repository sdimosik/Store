package ru.ozon.route256.core_storage_impl.data

import androidx.core.content.edit
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_storage_api.model.ProductEntity
import ru.ozon.route256.core_storage_api.model.ProductEntityList
import ru.ozon.route256.core_storage_api.model.ProductInListEntity
import ru.ozon.route256.core_storage_api.model.ProductInListEntityList
import ru.ozon.route256.core_storage_impl.toList
import ru.ozon.route256.core_storage_impl.toProductInListEntity
import ru.ozon.route256.core_utils.Utils
import ru.ozon.route256.core_utils.getParcelable
import ru.ozon.route256.core_utils.putParcelable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheApiImpl @Inject constructor(

) : CacheApi {

    companion object {
        private const val CACHE_PRODUCT_LIST = "CACHE_PRODUCT_LIST"
        private const val CACHE_PRODUCTS = "CACHE_PRODUCTS"
    }

    override suspend fun updateCacheProductList(list: List<ProductInListEntity>) {
        val a = 5
    }

    override suspend fun getCacheProductList(): List<ProductInListEntity> {
        return emptyList()
    }

    override suspend fun updateCacheProducts(list: List<ProductEntity>) {
        val a = 5
    }

    override suspend fun getCacheProducts(): List<ProductEntity> {
        return emptyList()
    }

    override suspend fun addProduct(product: ProductEntity) {
        val a = 5
    }

//    override suspend  fun updateCacheProductList(list: List<ProductInListEntity>) {
//        val data = ProductInListEntityList(list)
//        Utils.getSharedPreference().edit {
//            putParcelable(CACHE_PRODUCT_LIST, data)
//        }
//    }
//
//    override suspend fun getCacheProductList(): List<ProductInListEntity> {
//        return Utils.getSharedPreference().getParcelable<ProductInListEntityList?>(
//            CACHE_PRODUCT_LIST,
//            null
//        )?.toList() ?: mutableListOf()
//    }
//
//    override suspend fun updateCacheProducts(list: List<ProductEntity>) {
//        val data = ProductEntityList(list)
//        Utils.getSharedPreference().edit {
//            putParcelable(CACHE_PRODUCTS, data)
//        }
//    }
//
//    override suspend fun getCacheProducts(): List<ProductEntity> {
//        return Utils.getSharedPreference().getParcelable<ProductEntityList?>(
//            CACHE_PRODUCTS,
//            null
//        )?.toList() ?: mutableListOf()
//    }
//
//    override suspend fun addProduct(product: ProductEntity) {
//        val cache_products = getCacheProducts().toMutableList()
//        cache_products.add(product)
//
//        val cache_product_list = getCacheProductList().toMutableList()
//        cache_product_list.add(product.toProductInListEntity())
//
//        Utils.getSharedPreference().edit {
//            putParcelable(CACHE_PRODUCT_LIST, ProductInListEntityList(cache_product_list))
//        }
//
//        Utils.getSharedPreference().edit {
//            putParcelable(CACHE_PRODUCTS, ProductEntityList(cache_products))
//        }
//    }
}