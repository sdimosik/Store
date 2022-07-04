package ru.ozon.route256.core_navigation_impl.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.core_navigation_api.NavigationApi
import ru.ozon.route256.core_navigation_impl.MainActivity
import ru.ozon.route256.core_network_impl.di.CoreNetworkComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NavigationModule::class,
        NavigationHelperModule::class
    ],
)
interface CoreNavigationComponent : NavigationApi {
    companion object {
        @Volatile
        private var coreNavigationComponent: CoreNavigationComponent? = null

        fun get(application: Application): CoreNavigationComponent? {
            if (coreNavigationComponent == null) {
                synchronized(CoreNetworkComponent::class.java) {
                    if (coreNavigationComponent == null) {
                        coreNavigationComponent = DaggerCoreNavigationComponent.builder()
                            .applicationContext(application.applicationContext)
                            .build()
                    }
                }
            }
            return coreNavigationComponent
        }
    }

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): CoreNavigationComponent
    }
}