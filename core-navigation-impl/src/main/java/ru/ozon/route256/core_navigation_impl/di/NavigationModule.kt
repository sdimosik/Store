package ru.ozon.route256.core_navigation_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_navigation_impl.navigation.AddProductNavigationImpl
import ru.ozon.route256.core_navigation_impl.navigation.PDPNavigationImpl
import ru.ozon.route256.core_navigation_impl.navigation.ProductNavigationImpl
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_products_api.ProductNavigationApi

@Module
interface NavigationModule {

    @Binds
    fun bindProductNavigation(productNavigationApi: ProductNavigationImpl): ProductNavigationApi

    @Binds
    fun bindPDPNavigation(pdpNavigationImpl: PDPNavigationImpl): PDPNavigationApi

    @Binds
    fun bindAddProductNavigation(addProductNavigationImpl: AddProductNavigationImpl): AddProductNavigationApi
}