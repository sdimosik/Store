package ru.ozon.route256.feature_products_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_storage_api.StorageApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class
    ],
    dependencies = [
        ProductFeatureDependencies::class
    ]
)
@PerFeature
interface ProductFeatureComponent {

    companion object {

        @Volatile
        var productFeatureComponent: ProductFeatureComponent? = null
            private set

        fun initAndGet(productFeatureDependencies: ProductFeatureDependencies): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                synchronized(ProductFeatureComponent::class) {
                    productFeatureComponent = DaggerProductFeatureComponent.builder()
                        .productFeatureDependencies(productFeatureDependencies)
                        .build()
                }
            }
            return productFeatureComponent
        }

        fun get(): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(): ProductFeatureComponent' method")
            }
            return productFeatureComponent
        }

        fun resetComponent() {
            productFeatureComponent = null
        }
    }

    fun inject(fragment: ProductsFragment)

    @PerFeature
    @Component(
        dependencies = [
            NetworkApi::class,
            ProductNavigationApi::class,
            StorageApi::class
        ]
    )
    interface ProductFeatureDependenciesComponent : ProductFeatureDependencies
}