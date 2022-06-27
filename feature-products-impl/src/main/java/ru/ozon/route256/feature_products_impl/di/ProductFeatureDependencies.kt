package ru.ozon.route256.feature_products_impl.di

import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi

interface ProductFeatureDependencies {
    fun productApi(): ProductApi
    fun productNavigationApi(): ProductNavigationApi
    fun cacheApi(): CacheApi
}