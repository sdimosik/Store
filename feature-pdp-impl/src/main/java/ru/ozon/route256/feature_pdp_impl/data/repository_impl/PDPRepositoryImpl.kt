package ru.ozon.route256.feature_pdp_impl.data.repository_impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.feature_pdp_impl.data.mapper.toDomain
import ru.ozon.route256.feature_pdp_impl.domain.model.ProductDomain
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import javax.inject.Inject

class PDPRepositoryImpl @Inject constructor(
    private val apiService: ProductApi
) : PDPRepository {

    companion object {

        // cache moment
        private var isFirstCacheList = true
        //private var cacheList = mutableListOf<ProductInListDomain>()

        private val isFirstCacheDetailProduct = true
        //private var cacheDetailProduct = mutableListOf<ProductDomain>()
    }

    override suspend fun getProductById(guid: String): ProductDomain? =
        withContext(Dispatchers.IO) {
            val response = apiService.getProducts()

//                if (!response.isSuccessful)
//                    return@withContext null
//
//                if (response.body() == null)
//                    return@withContext null
//
//                cacheDetailProduct.addAll(response.body()!!.map {
//                    it.toDomain()
//                }.toMutableList())

            return@withContext response
                .find { it.guid == guid }?.toDomain()

        }
}