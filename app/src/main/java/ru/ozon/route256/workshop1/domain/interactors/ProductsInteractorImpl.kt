package ru.ozon.route256.workshop1.domain.interactors

import ru.ozon.route256.workshop1.domain.mapper.toUI
import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.repositories.ProductsRepository
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI
import ru.ozon.route256.workshop1.presentation.model.ProductUI

class ProductsInteractorImpl(
    private val rep: ProductsRepository
) : ProductsInteractor {
    override suspend fun getProductsList(): List<ProductInListUI> {
        return rep.getProducts().map {
            it.toUI()
        }
    }

    override suspend fun getProductById(guid: String): ProductUI? {
        return rep.getProductById(guid)?.toUI()
    }

    override suspend fun addProduct(productDomain: ProductDomain) {
        return rep.addProduct(productDomain)
    }

    override suspend fun addCountView(id: String?) {
        rep.addCountView(id)
    }
}