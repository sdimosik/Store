package ru.ozon.route256.workshop1.data.repositoriesImpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ozon.route256.workshop1.data.mapper.toDomain
import ru.ozon.route256.workshop1.data.network.ApiService
import ru.ozon.route256.workshop1.domain.mapper.toProductInListData
import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain
import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository

class MockProductsRepositoryImpl(
    private val apiService: ApiService
) : ProductsRepository {

    companion object {

        // cache moment
        private var isFirstCacheList = true
        private var cacheList = mutableListOf<ProductInListDomain>()

        private val isFirstCacheDetailProduct = true
        private var cacheDetailProduct = mutableListOf<ProductDomain>()
    }

    override suspend fun getProducts(): List<ProductInListDomain> = withContext(Dispatchers.IO) {
        if (isFirstCacheList) {
            val response = apiService.getProductsInList()

            if (!response.isSuccessful)
                return@withContext cacheList

            if (response.body() == null)
                return@withContext cacheList

            cacheList.addAll(
                response.body()!!.map {
                    it.toDomain()
                }.toMutableList()
            )

            isFirstCacheList = false
            return@withContext cacheList
        } else {
            return@withContext cacheList
        }
    }

    override suspend fun getProductById(guid: String): ProductDomain? =
        withContext(Dispatchers.IO) {

            if (isFirstCacheDetailProduct) {
                val response = apiService.getProducts()

                if (!response.isSuccessful)
                    return@withContext null

                if (response.body() == null)
                    return@withContext null

                cacheDetailProduct.addAll(response.body()!!.map {
                    it.toDomain()
                }.toMutableList())

                return@withContext cacheDetailProduct
                    .find { it.guid == guid }

            } else {
                return@withContext cacheDetailProduct
                    .find { it.guid == guid }
            }
        }

    override suspend fun addProduct(productDomain: ProductDomain) {
        withContext(Dispatchers.IO){
            cacheDetailProduct.add(productDomain)
            cacheList.add(productDomain.toProductInListData())
        }
    }

    override suspend fun addCountView(id: String?) {
        withContext(Dispatchers.IO){
            cacheList.mapIndexed { _, productInListUI ->
                if (productInListUI.guid == id) {
                    productInListUI.countView += 1
                    return@mapIndexed
                }
            }
        }
    }
}