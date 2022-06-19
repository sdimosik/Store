package ru.ozon.route256.core_storage_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.core_storage_api.CacheApi
import ru.ozon.route256.core_storage_impl.data.CacheApiImpl

@Module
abstract class StorageModule {

    @Binds
    abstract fun bindCacheApi(cacheApiImpl: CacheApiImpl): CacheApi
}