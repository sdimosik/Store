package ru.ozon.route256.feature_add_product_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ozon.route256.core_utils.ViewModelFactory
import ru.ozon.route256.core_utils.di.ViewModelKey
import ru.ozon.route256.feature_add_product_impl.presentation.view_model.AddProductViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel::class)
    fun bindAddProductViewModel(addProductViewModel: AddProductViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}