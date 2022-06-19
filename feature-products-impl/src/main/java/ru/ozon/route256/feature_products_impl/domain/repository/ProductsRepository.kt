package ru.ozon.route256.feature_products_impl.domain.repository

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain

interface ProductsRepository {

    suspend fun getProducts(): List<ProductInListDomain>
    suspend fun addCountView(id: String?)
}