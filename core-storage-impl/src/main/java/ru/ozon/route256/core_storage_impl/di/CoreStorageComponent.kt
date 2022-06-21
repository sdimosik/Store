package ru.ozon.route256.core_storage_impl.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
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

        fun get(applicationContext: Context): CoreStorageComponent? {
            if (sCoreDbComponent == null) {
                synchronized(CoreStorageComponent::class.java) {
                    if (sCoreDbComponent == null) {
                        sCoreDbComponent = DaggerCoreStorageComponent.builder()
                            .applicationContext(applicationContext)
                            .build()
                    }
                }
            }
            return sCoreDbComponent
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): CoreStorageComponent
    }
}