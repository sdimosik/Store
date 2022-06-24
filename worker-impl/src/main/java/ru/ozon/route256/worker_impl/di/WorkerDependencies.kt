package ru.ozon.route256.worker_impl.di

import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_storage_api.CacheApi

interface WorkerDependencies {
    fun productApi(): ProductApi
    fun cacheApi(): CacheApi
}