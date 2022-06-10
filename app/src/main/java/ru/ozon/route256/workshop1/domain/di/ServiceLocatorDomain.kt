package ru.ozon.route256.workshop1.domain.di

import ru.ozon.route256.workshop1.data.di.ServiceLocatorData
import ru.ozon.route256.workshop1.data.repositoriesImpl.MockProductsRepositoryImpl
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractorImpl

object ServiceLocatorDomain {
    fun provideProductsInteractor(): ProductsInteractor {
        val productsInteractor: ProductsInteractor by lazy {
            ProductsInteractorImpl(
                MockProductsRepositoryImpl(ServiceLocatorData.provideApiService)
            )
        }
        return productsInteractor
    }
}