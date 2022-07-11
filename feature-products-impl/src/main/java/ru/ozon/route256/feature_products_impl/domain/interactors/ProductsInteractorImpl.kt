package ru.ozon.route256.feature_products_impl.domain.interactors

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import ru.ozon.route256.feature_products_impl.domain.mapper.toUI
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository
import ru.ozon.route256.feature_products_impl.presentation.mapper.toDomain
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI
import javax.inject.Inject

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

    override fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>> = rep.loadContent(forceRefresh)
    override suspend fun updateProductItemInList(product: ProductInListUI) {
        rep.updateProductItemInList(product.toDomain())
    }
}