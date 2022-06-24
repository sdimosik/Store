package ru.ozon.route256.worker_impl.di

import dagger.Component
import ru.ozon.route256.core_network_api.NetworkApi
import ru.ozon.route256.core_storage_api.StorageApi
import ru.ozon.route256.worker_api.WorkerApi
import ru.ozon.route256.worker_impl.data.SampleWorkerFactory

@Component(
    modules = [
        WorkerModule::class,
        //
        // WorkerBindingModule::class
    ],
    dependencies = [
        WorkerDependencies::class
    ]
)
interface WorkerComponent : WorkerApi {

    companion object {
        @Volatile
        private var workerComponent: WorkerComponent? = null

        fun get(workerDependencies: WorkerDependencies): WorkerComponent? {
            if (workerComponent == null) {
                synchronized(WorkerComponent::class.java) {
                    if (workerComponent == null) {
                        workerComponent = DaggerWorkerComponent.builder()
                            .workerDependencies(workerDependencies)
                            .build()
                    }
                }
            }
            return workerComponent
        }
    }

    @Component(
        dependencies = [
            NetworkApi::class,
            StorageApi::class
        ]
    )
    interface WorkerDependenciesComponent : WorkerDependencies

    fun sampleWorkerFactory(): SampleWorkerFactory
}