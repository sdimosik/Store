package ru.ozon.route256.feature_pdp_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.ozon.route256.core_utils.ViewModelFactory
import ru.ozon.route256.core_utils.di.ViewModelKey
import ru.ozon.route256.feature_pdp_impl.presentation.view_model.PDPViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PDPViewModel::class)
    fun bindPDPViewModel(pdpViewModel: PDPViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}