package ru.ozon.route256.worker_impl.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ozon.route256.worker_impl.data.ChildWorkerFactory
import ru.ozon.route256.worker_impl.data.DetailProductsWorker
import ru.ozon.route256.worker_impl.data.ProductListWorker

//@Module
//interface WorkerBindingModule {
//    @Binds
//    @IntoMap
//    @WorkerKey(ProductListWorker::class)
//    fun bindProductListWorker(factory: ProductListWorker.Factory): ChildWorkerFactory
//
//    @Binds
//    @IntoMap
//    @WorkerKey(DetailProductsWorker::class)
//    fun bindDetailProductsWorker(factory: DetailProductsWorker.Factory): ChildWorkerFactory
//}