package ru.ozon.route256.core_storage_impl.data

import android.content.Context
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
    private val context: Context
) : CacheApi {

    companion object {
        private const val CACHE_PRODUCT_LIST = "CACHE_PRODUCT_LIST"
        private const val CACHE_PRODUCTS = "CACHE_PRODUCTS"

        private var cacheList = mutableListOf<ProductInListEntity>()
        private var cacheDetailProduct = mutableListOf<ProductEntity>()
    }

//    override suspend fun updateCacheProductList(list: List<ProductInListEntity>) {
//        cacheList = list.toMutableList()
//    }
//
//    override suspend fun getCacheProductList(): List<ProductInListEntity> {
//        return cacheList
//    }
//
//    override suspend fun updateCacheProducts(list: List<ProductEntity>) {
//        cacheDetailProduct = list.toMutableList()
//    }
//
//    override suspend fun getCacheProducts(): List<ProductEntity> {
//        return cacheDetailProduct
//    }
//
//    override suspend fun addProduct(product: ProductEntity) {
//        cacheDetailProduct.add(product)
//        cacheList.add(product.toProductInListEntity())
//    }

    override suspend fun updateCacheProductList(list: List<ProductInListEntity>) {
        val data = ProductInListEntityList(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCT_LIST, data)
        }
    }

    override suspend fun getCacheProductList(): List<ProductInListEntity>? {
        return context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getParcelable<ProductInListEntityList?>(
                CACHE_PRODUCT_LIST,
                null
            )?.toList()
    }

    override suspend fun updateCacheProducts(list: List<ProductEntity>) {
        val data = ProductEntityList(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCTS, data)
        }
    }

    override suspend fun getCacheProducts(): List<ProductEntity>? {
        return context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getParcelable<ProductEntityList?>(
                CACHE_PRODUCTS,
                null
            )?.toList()
    }

    override suspend fun addProduct(product: ProductEntity) {
        val cache_products = getCacheProducts()?.toMutableList() ?: mutableListOf()
        cache_products.add(product)

        val cache_product_list = getCacheProductList()?.toMutableList() ?: mutableListOf()
        cache_product_list.add(product.toProductInListEntity())

        val pref = context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

        pref.edit {
            putParcelable(CACHE_PRODUCT_LIST, ProductInListEntityList(cache_product_list))
            putParcelable(CACHE_PRODUCTS, ProductEntityList(cache_products))
        }
    }
}