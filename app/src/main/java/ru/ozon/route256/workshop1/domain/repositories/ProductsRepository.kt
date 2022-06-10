package ru.ozon.route256.workshop1.domain.repositories

import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain

interface ProductsRepository {

    suspend fun getProducts(): List<ProductInListDomain>

    suspend fun getProductById(guid: String): ProductDomain?
}