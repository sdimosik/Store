package ru.ozon.route256.workshop1.data.repositoriesImpl

import ru.ozon.route256.workshop1.data.mapper.toDomain
import ru.ozon.route256.workshop1.data.network.ApiService
import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain
import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository

class MockProductsRepositoryImpl(
    private val apiService: ApiService
) : ProductsRepository {

    override suspend fun getProducts(): List<ProductInListDomain> {
        val response = apiService.getProductsInList()

        if (!response.isSuccessful)
            return emptyList()

        if (response.body() == null)
            return emptyList()

        return response.body()!!.map {
            it.toDomain()
        }
    }

    override suspend fun getProductById(guid: String): ProductDomain? {

        val response = apiService.getProducts()

        if (!response.isSuccessful)
            return null

        if (response.body() == null)
            return null

        return response.body()!!
            .find { it.guid == guid }
            ?.toDomain()
    }
}