package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_storage_api.model.InCartGuidEntity
import ru.ozon.route256.feature_pdp_impl.data.mapper.toDomain
import ru.ozon.route256.feature_pdp_impl.domain.model.ProductDomain
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(
    private val apiService: ProductApi,
    private val cacheApi: CacheApi
) : PDPRepository {

    override suspend fun getProductById(guid: String): ProductDomain? =
        withContext(Dispatchers.IO) {
            val response = cacheApi.getCacheProducts()
            return@withContext response?.find { it.guid == guid }?.toDomain()
        }

    override suspend fun getCartCount(guid: String?): Int =
        withContext(Dispatchers.IO) {
            val item = cacheApi.getInCart()?.find { it.guid == guid }
            return@withContext item?.count ?: 0
        }

    override suspend fun updateCountInCart(guid: String?, count: Int) =
        withContext(Dispatchers.IO) {
            Log.d("PDPRep", "$guid count: $count")
            val list = cacheApi.getInCart() ?: mutableListOf()
            list.forEach {
                if (it.guid == guid) {
                    it.count = count
                    return@forEach
                }
            }
            cacheApi.updateInCart(list)
        }
}