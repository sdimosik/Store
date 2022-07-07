package ru.ozon.route256.feature_products_impl.domain.interactors

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

interface ProductsInteractor {
    suspend fun getProductsList(): List<ProductInListUI>
    suspend fun addCountView(id: String?)
    fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>>
    suspend fun updateProductItemInList(product: ProductInListUI)
}