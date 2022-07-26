package ru.ozon.route256.feature_pdp_impl.domain.repository

import ru.ozon.route256.feature_pdp_impl.domain.model.ProductDomain


interface PDPRepository {

    suspend fun getProductById(guid: String): ProductDomain?
    suspend fun getCartCount(guid: String?): Int
    suspend fun updateCountInCart(guid: String?, count: Int)
}