package ru.ozon.route256.feature_products_impl.domain.repository

import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import com.google.common.util.concurrent.ListenableFuture
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain

interface ProductsRepository {

    suspend fun getProducts(): List<ProductInListDomain>
    suspend fun addCountView(id: String?)
    fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>>
    suspend fun updateProductItemInList(toDomain: ProductInListDomain)
}