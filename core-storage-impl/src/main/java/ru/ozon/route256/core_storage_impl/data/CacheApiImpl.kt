package ru.ozon.route256.core_storage_impl.data

import android.content.Context
import androidx.core.content.edit
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_storage_api.model.*
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

        private const val CACHE_PRODUCT_LIST_ADD = "CACHE_PRODUCT_LIST_ADD"
        private const val CACHE_PRODUCTS_ADD = "CACHE_PRODUCTS_ADD"

        private const val CACHE_PRODUCT_GUID_IN_CART = "CACHE_PRODUCT_GUID_IN_CART"
    }

    override suspend fun updateCacheProductList(list: List<ProductInListEntity>) {
        val data = ProductInListEntityList(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCT_LIST, data)
        }
    }

    override suspend fun getCacheProductList(): List<ProductInListEntity>? {
        val mainCache =
            context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getParcelable<ProductInListEntityList?>(
                    CACHE_PRODUCT_LIST,
                    null
                )?.toList()
        val secondCache = getAddCacheProductList()?.toList()
        val inCartMap = getInCart()?.toList()?.associate { it.guid to it.count }
        return if (mainCache == null && secondCache == null) {
            null
        } else {
            val result = (mainCache ?: mutableListOf()).plus(secondCache ?: mutableListOf())
            result.forEach { product ->
                val count = inCartMap?.get(product.guid)
                product.isInCart = count != null && count > 0
            }
            result
        }
    }

    override suspend fun getOnlyCacheProductList(): List<ProductInListEntity>? {
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
        val mainCache =
            context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
                .getParcelable<ProductEntityList?>(
                    CACHE_PRODUCTS,
                    null
                )?.toList()
        val secondCache = getAddCacheProducts()?.toList()
        return if (mainCache == null && secondCache == null) {
            null
        } else {
            (mainCache ?: mutableListOf()).plus(secondCache ?: mutableListOf())
        }
    }

    override suspend fun updateAddCacheProductList(list: List<ProductInListEntity>) {
        val data = ProductInListEntityList(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCT_LIST_ADD, data)
        }
    }

    override suspend fun getAddCacheProductList(): List<ProductInListEntity>? {
        return context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getParcelable<ProductInListEntityList?>(
                CACHE_PRODUCT_LIST_ADD,
                null
            )?.toList()
    }

    override suspend fun updateAddCacheProducts(list: List<ProductEntity>) {
        val data = ProductEntityList(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCTS_ADD, data)
        }
    }

    override suspend fun getAddCacheProducts(): List<ProductEntity>? {
        return context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getParcelable<ProductEntityList?>(
                CACHE_PRODUCTS_ADD,
                null
            )?.toList()
    }

    override suspend fun addProduct(product: ProductEntity) {
        val cache_products = getAddCacheProducts()?.toMutableList() ?: mutableListOf()
        cache_products.add(product)

        val cache_product_list = getAddCacheProductList()?.toMutableList() ?: mutableListOf()
        cache_product_list.add(product.toProductInListEntity())

        val pref = context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

        pref.edit {
            putParcelable(CACHE_PRODUCT_LIST_ADD, ProductInListEntityList(cache_product_list))
            putParcelable(CACHE_PRODUCTS_ADD, ProductEntityList(cache_products))
        }
    }

    override suspend fun updateInCart(list: List<InCartGuidEntity>) {
        val data = InCartGuidListEntity(list)
        context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE).edit {
            putParcelable(CACHE_PRODUCT_GUID_IN_CART, data)
        }
    }

    override suspend fun getInCart(): List<InCartGuidEntity>? {
        return context.getSharedPreferences(Utils.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            .getParcelable<InCartGuidListEntity?>(
                CACHE_PRODUCT_GUID_IN_CART,
                null
            )?.toList()
    }
}