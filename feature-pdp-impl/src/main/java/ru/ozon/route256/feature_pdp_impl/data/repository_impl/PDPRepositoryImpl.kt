package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
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
}