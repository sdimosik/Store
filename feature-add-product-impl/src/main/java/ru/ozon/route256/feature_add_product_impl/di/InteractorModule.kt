package ru.ozon.route256.feature_add_product_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_add_product_impl.domain.interactors.AddProductInteractor
import ru.ozon.route256.feature_add_product_impl.domain.interactors.AddProductInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindAddProductInteractor(interactorImpl: AddProductInteractorImpl): AddProductInteractor
}