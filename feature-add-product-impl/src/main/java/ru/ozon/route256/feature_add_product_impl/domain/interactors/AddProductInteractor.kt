package ru.ozon.route256.feature_add_product_impl.domain.interactors

import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

interface AddProductInteractor {
    suspend fun addProduct(productDomain: AddProductDomain)
}

class AddProductInteractorImpl @Inject constructor(
    private val rep: AddProductRepository
) : AddProductInteractor {
    override suspend fun addProduct(productDomain: AddProductDomain) {
        rep.addProduct(productDomain)
    }
}