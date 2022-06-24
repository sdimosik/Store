package ru.ozon.route256.feature_pdp_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_storage_api.StorageApi
import ru.ozon.route256.core_utils.di.PerFeature
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        PDPFeatureDependencies::class
    ]
)
@PerFeature
interface PDPFeatureComponent {

    companion object {

        @Volatile
        var pdpFeatureComponent: PDPFeatureComponent? = null
            private set

        fun initAndGet(PDPFeatureDependencies: PDPFeatureDependencies): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                synchronized(PDPFeatureComponent::class) {
                    pdpFeatureComponent = DaggerPDPFeatureComponent.builder()
                        .pDPFeatureDependencies(PDPFeatureDependencies)
                        .build()
                }
            }
            return pdpFeatureComponent
        }

        fun get(): PDPFeatureComponent? {
            if (pdpFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(): PDPFeatureDependencies' method")
            }
            return pdpFeatureComponent
        }

        fun resetComponent() {
            pdpFeatureComponent = null
        }
    }

    fun inject(fragment: PDPFragment)

    @PerFeature
    @Component(
        dependencies = [
            NetworkApi::class,
            PDPNavigationApi::class,
            StorageApi::class
        ]
    )
    interface PDPFeatureDependenciesComponent : PDPFeatureDependencies
}