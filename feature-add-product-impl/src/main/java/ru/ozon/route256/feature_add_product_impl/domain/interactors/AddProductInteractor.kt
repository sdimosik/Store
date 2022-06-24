package ru.ozon.route256.feature_add_product_impl.domain.interactors

import ru.ozon.route256.feature_add_product_impl.domain.mapper.toUI
import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.domain.repository.AddProductRepository
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI
import javax.inject.Inject

interface AddProductInteractor {
    suspend fun addProduct(productDomain: AddProductDomain)
    suspend fun getProductById(guid: String): AddProductUI?
}

class AddProductInteractorImpl @Inject constructor(
    private val rep: AddProductRepository
) : AddProductInteractor {
    override suspend fun addProduct(productDomain: AddProductDomain) {
        rep.addProduct(productDomain)
    }

    override suspend fun getProductById(guid: String): AddProductUI? {
        return rep.getProductById(guid)?.toUI()
    }
}