package ru.ozon.route256.workshop1

import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton

@Singleton
@Component
abstract class AppComponent {

    companion object {
        private lateinit var appComponent: AppComponent

        fun get(): AppComponent {
            return Preconditions.checkNotNull(
                appComponent,
                "AppComponent is not initialized yet. Call init first."
            )
        }

        fun init(component: AppComponent) {
            require(!this::appComponent.isInitialized) {
                "AppComponent is already initialized."
            }
            appComponent = component
        }
    }

    abstract fun inject(app: App)
}
