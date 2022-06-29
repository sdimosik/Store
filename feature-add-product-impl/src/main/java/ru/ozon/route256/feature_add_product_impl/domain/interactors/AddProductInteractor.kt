package ru.ozon.route256.feature_add_product_impl.domain.interactors

import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI

interface AddProductInteractor {
    suspend fun addProduct(productDomain: AddProductDomain)
    suspend fun getProductById(guid: String): AddProductUI?
}