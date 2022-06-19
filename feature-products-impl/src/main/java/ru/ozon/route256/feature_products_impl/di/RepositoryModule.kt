package ru.ozon.route256.feature_products_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_products_impl.data.repository_impl.ProductsRepositoryImpl
import ru.ozon.route256.feature_products_impl.domain.repository.ProductsRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindProductsRepository(repository: ProductsRepositoryImpl): ProductsRepository
}