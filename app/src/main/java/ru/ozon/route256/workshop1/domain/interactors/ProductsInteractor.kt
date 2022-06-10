package ru.ozon.route256.workshop1.domain.interactors

import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI
import ru.ozon.route256.workshop1.presentation.model.ProductUI

interface ProductsInteractor {

    suspend fun getProductsList(): List<ProductInListUI>

    suspend fun getProductById(guid: String): ProductUI?

    fun addProduct(productDomain: ProductDomain)
}