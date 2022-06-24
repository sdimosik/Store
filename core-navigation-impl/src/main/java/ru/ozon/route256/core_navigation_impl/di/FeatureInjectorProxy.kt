package ru.ozon.route256.core_navigation_impl.di

import android.app.Application
import ru.ozon.route256.core_network_impl.di.CoreNetworkComponent
import ru.ozon.route256.core_storage_impl.di.CoreStorageComponent
import ru.ozon.route256.feature_add_product_impl.di.AddProductFeatureComponent
import ru.ozon.route256.feature_add_product_impl.di.DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent
import ru.ozon.route256.feature_pdp_impl.di.PDPFeatureComponent
import ru.ozon.route256.feature_products_impl.di.DaggerProductFeatureComponent_ProductFeatureDependenciesComponent
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.worker_impl.di.DaggerWorkerComponent_WorkerDependenciesComponent
import ru.ozon.route256.worker_impl.di.WorkerComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI(application: Application) {
        ProductFeatureComponent.initAndGet(
            application,
            DaggerProductFeatureComponent_ProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get(application))
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .storageApi(CoreStorageComponent.get(application))
                .workerApi(
                    WorkerComponent.get(
                        DaggerWorkerComponent_WorkerDependenciesComponent.builder()
                            .networkApi(CoreNetworkComponent.get(application))
                            .storageApi(CoreStorageComponent.get(application))
                            .build()
                    )
                )
                .build()
        )
    }

    fun initFeaturePDPDI(application: Application) {
        PDPFeatureComponent.initAndGet(
            DaggerPDPFeatureComponent_PDPFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get(application))
                .pDPNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getPDPNavigation()
                )
                .storageApi(CoreStorageComponent.get(application))
                .build()
        )
    }

    fun initFeatureAddProductDI(application: Application) {
        AddProductFeatureComponent.initAndGet(
            DaggerAddProductFeatureComponent_AddProductFeatureDependenciesComponent.builder()
                .networkApi(CoreNetworkComponent.get(application))
                .addProductNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getAddProductNavigation()
                )
                .storageApi(CoreStorageComponent.get(application))
                .build()
        )
    }
}