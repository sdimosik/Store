package ru.ozon.route256.feature_add_product_impl.domain.repository

import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain

interface AddProductRepository {

    suspend fun addProduct(productDomain: AddProductDomain)
    suspend fun getProductById(guid: String): AddProductDomain?
}