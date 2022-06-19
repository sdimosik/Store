package ru.ozon.route256.feature_add_product_impl.di

import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi

interface AddProductFeatureDependencies {
    fun productApi(): ProductApi
    fun productNavigationApi(): AddProductNavigationApi
    fun cacheApi(): CacheApi
}