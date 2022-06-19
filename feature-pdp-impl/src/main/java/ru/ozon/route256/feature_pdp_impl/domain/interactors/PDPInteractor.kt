package ru.ozon.route256.feature_pdp_impl.domain.interactors

import ru.ozon.route256.feature_pdp_impl.domain.mapper.toUI
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductUI
import javax.inject.Inject

interface PDPInteractor {
    suspend fun getProductById(it: String): ProductUI?
}

class PDPInteractorImpl @Inject constructor(
    private val rep: PDPRepository
) : PDPInteractor {
    override suspend fun getProductById(it: String): ProductUI? {
        return rep.getProductById(it)?.toUI()
    }
}