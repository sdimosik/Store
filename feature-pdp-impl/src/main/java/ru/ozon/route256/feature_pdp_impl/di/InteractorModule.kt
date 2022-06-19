package ru.ozon.route256.feature_pdp_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.domain.interactors.PDPInteractor
import ru.ozon.route256.feature_pdp_impl.domain.interactors.PDPInteractorImpl

@Module
interface InteractorModule {

    @Binds
    fun bindPDPInteractor(interactorImpl: PDPInteractorImpl): PDPInteractor
}