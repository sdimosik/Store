package ru.ozon.route256.feature_products_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractor
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindProductsInteractor(interactorImpl: ProductsInteractorImpl): ProductsInteractor
}