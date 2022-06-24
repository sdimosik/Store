package ru.ozon.route256.workshop1

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import ru.ozon.route256.core_network_impl.di.CoreNetworkComponent
import ru.ozon.route256.core_storage_impl.di.CoreStorageComponent
import ru.ozon.route256.worker_impl.data.SampleWorkerFactory
import ru.ozon.route256.worker_impl.di.DaggerWorkerComponent_WorkerDependenciesComponent
import ru.ozon.route256.worker_impl.di.WorkerComponent
import javax.inject.Inject

class App : Application() {

    companion object {
        @Volatile
        private lateinit var sContext: Context

        fun getAppContext(): Context {
            return sContext
        }
    }

    @Inject
    lateinit var sampleWorkerFactory: SampleWorkerFactory

    override fun onCreate() {
        super.onCreate()
        sContext = this.applicationContext

        AppComponent.init(
            DaggerAppComponent.builder()
                .workerComponent(
                    WorkerComponent.get(
                        DaggerWorkerComponent_WorkerDependenciesComponent.builder()
                            .networkApi(CoreNetworkComponent.get(this))
                            .storageApi(CoreStorageComponent.get(this))
                            .build()
                    )
                )
                .build()
        )
        AppComponent.get().inject(this)

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(sampleWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)
    }
}