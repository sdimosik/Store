package ru.ozon.route256.core_network_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface CoreNetworkComponent : NetworkApi{
    companion object{
        @Volatile
        private var sCoreNetworkComponent: CoreNetworkComponent? = null

        fun get(): CoreNetworkComponent? {
            if (sCoreNetworkComponent == null) {
                synchronized(CoreNetworkComponent::class.java) {
                    if (sCoreNetworkComponent == null) {
                        sCoreNetworkComponent = DaggerCoreNetworkComponent.builder().build()
                    }
                }
            }
            return sCoreNetworkComponent
        }
    }
}