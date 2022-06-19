package ru.ozon.route256.feature_add_product_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_add_product_impl.data.repository_impl.AddProductRepositoryImpl
import ru.ozon.route256.feature_add_product_impl.domain.repository.AddProductRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindAddProductRepository(repository: AddProductRepositoryImpl): AddProductRepository
}