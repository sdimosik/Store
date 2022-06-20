package ru.ozon.route256.core_navigation_impl.di

import ru.ozon.route256.core_network_impl.di.CoreNetworkComponent
import ru.ozon.route256.core_storage_impl.di.CoreStorageComponent
import ru.ozon.route256.feature_add_product_impl.di.AddProductFeatureComponent
import ru.ozon.route256.feature_add_product_impl.di.DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.PDPFeatureComponent
import ru.ozon.route256.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI() {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get())
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .storageApi(CoreStorageComponent.get())
                .build()
        )
    }

    fun initFeaturePDPDI() {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get())
                .pDPNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getPDPNavigation()
                )
                .storageApi(CoreStorageComponent.get())
                .build()
        )
    }

    fun initFeatureAddProductDI() {
        AddProductFeatureComponent.initAndGet(
            DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get())
                .addProductNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getAddProductNavigation()
                )
                .storageApi(CoreStorageComponent.get())
                .build()
        )
    }
}