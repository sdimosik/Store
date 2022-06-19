package ru.ozon.route256.core_navigation_api

import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi

interface NavigationApi {
    fun getProductNavigation(): ProductNavigationApi
    fun getPDPNavigation(): PDPNavigationApi
    fun getAddProductNavigation(): AddProductNavigationApi
}