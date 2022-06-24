package ru.ozon.route256.feature_add_product_impl.data.repository_impl

import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.feature_add_product_impl.data.mapper.toEntity
import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    private val cacheApi: CacheApi
) : AddProductRepository {

    override suspend fun addProduct(productDomain: AddProductDomain) {
        cacheApi.addProduct(productDomain.toEntity())
    }
}