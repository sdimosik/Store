package ru.ozon.route256.feature_products_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ozon.route256.core_utils.ViewModelFactory
import ru.ozon.route256.core_utils.di.ViewModelKey
import ru.ozon.route256.feature_products_impl.presentation.view_model.ProductsViewModel


@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindProductsViewModel(productsViewModel: ProductsViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}