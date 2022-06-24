package ru.ozon.route256.feature_products_impl.domain.interactors

import ru.ozon.route256.feature_products_impl.domain.mapper.toUI
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI
import javax.inject.Inject

interface ProductsInteractor {
    suspend fun getProductsList(): List<ProductInListUI>
    suspend fun addCountView(id: String?)
}

class ProductsInteractorImpl @Inject constructor(
    private val rep: ProductsRepository
) : ProductsInteractor {
    override suspend fun getProductsList(): List<ProductInListUI> {
        return rep.getProducts().map {
            it.toUI()
        }
    }

    override suspend fun addCountView(id: String?) {
        rep.addCountView(id)
    }
}