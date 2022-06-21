package ru.ozon.route256.core_network_impl.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreNetworkComponent : NetworkApi {
    companion object {
        @Volatile
        private var sCoreNetworkComponent: CoreNetworkComponent? = null

        fun get(application: Application): CoreNetworkComponent? {
            if (sCoreNetworkComponent == null) {
                synchronized(CoreNetworkComponent::class.java) {
                    if (sCoreNetworkComponent == null) {
                        sCoreNetworkComponent = DaggerCoreNetworkComponent.builder()
                            .applicationContext(application.applicationContext)
                            .build()
                    }
                }
            }
            return sCoreNetworkComponent
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): CoreNetworkComponent
    }
}