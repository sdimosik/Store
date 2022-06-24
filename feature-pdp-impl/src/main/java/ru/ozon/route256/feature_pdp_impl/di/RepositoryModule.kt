package ru.ozon.route256.feature_pdp_impl.di

import dagger.Binds
import dagger.Module
import ru.ozon.route256.feature_pdp_impl.data.repository_impl.PDPRepositoryImpl
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository

@Module
interface RepositoryModule {

    @Binds
    fun bindPDPRepository(repository: PDPRepositoryImpl): PDPRepository
}