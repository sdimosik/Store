package ru.ozon.route256.feature_pdp_impl.di

import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi

interface PDPFeatureDependencies {
    fun productApi(): ProductApi
    fun pdpNavigation(): PDPNavigationApi
}