package ru.ozon.route256.core_storage_impl.di

import dagger.Component
import ru.ozon.route256.core_storage_api.StorageApi
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        StorageModule::class
    ],
)
interface CoreStorageComponent : StorageApi {
    companion object {
        @Volatile
        private var sCoreDbComponent: CoreStorageComponent? = null

        fun get(): CoreStorageComponent? {
            if (sCoreDbComponent == null) {
                synchronized(CoreStorageComponent::class.java) {
                    if (sCoreDbComponent == null) {
                        sCoreDbComponent = DaggerCoreStorageComponent.builder().build()
                    }
                }
            }
            return sCoreDbComponent
        }
    }
}