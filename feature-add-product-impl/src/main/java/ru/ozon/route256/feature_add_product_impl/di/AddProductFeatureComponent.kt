package ru.ozon.route256.feature_add_product_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_storage_api.StorageApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_add_product_impl.presentation.view.AddProductFragment

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        AddProductFeatureDependencies::class
    ]
)
@PerFeature
interface AddProductFeatureComponent {

    companion object {

        @Volatile
        var addProductFeatureComponent: AddProductFeatureComponent? = null
            private set

        fun initAndGet(addProductFeatureDependencies: AddProductFeatureDependencies): AddProductFeatureComponent? {
            if (addProductFeatureComponent == null) {
                synchronized(AddProductFeatureComponent::class) {
                    addProductFeatureComponent = DaggerAddProductFeatureComponent.builder()
                        .addProductFeatureDependencies(addProductFeatureDependencies)
                        .build()
                }
            }
            return addProductFeatureComponent
        }

        fun get(): AddProductFeatureComponent? {
            if (addProductFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(): AddProductFeatureComponent' method")
            }
            return addProductFeatureComponent
        }

        fun resetComponent() {
            addProductFeatureComponent = null
        }
    }

    fun inject(fragment: AddProductFragment)

    @PerFeature
    @Component(
        dependencies = [
            NetworkApi::class,
            AddProductNavigationApi::class,
            StorageApi::class
        ]
    )
    interface AddProductFeatureDependenciesComponent : AddProductFeatureDependencies
}