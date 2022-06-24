package ru.ozon.route256.worker_impl.data

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider


//class SampleWorkerFactory @Inject constructor(
//    private val workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
//) : WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker? {
//        val foundEntry =
//            workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
//        val factoryProvider = foundEntry?.value
//            ?: throw IllegalArgumentException("unknown worker class name: $workerClassName")
//        return factoryProvider.get().create(appContext, workerParameters)
//    }
//}

class SampleWorkerFactory @Inject constructor(
    private val detailproductsworkerFactory: DetailProductsWorker.Factory,
    private val productlistworkerFactory: ProductListWorker.Factory
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            DetailProductsWorker::class.java.name ->
                detailproductsworkerFactory.create(appContext, workerParameters)
            ProductListWorker::class.java.name -> productlistworkerFactory.create(
                appContext,
                workerParameters
            )
            else -> null
        }
    }
}