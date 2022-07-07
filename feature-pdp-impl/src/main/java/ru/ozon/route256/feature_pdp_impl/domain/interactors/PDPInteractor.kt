package ru.ozon.route256.feature_pdp_impl.domain.interactors

import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductUI

interface PDPInteractor {
    suspend fun getProductById(it: String): ProductUI?
    suspend fun getCartCount(guid: String?): Int
    suspend fun updateCountInCart(guid: String?, count: Int)
}