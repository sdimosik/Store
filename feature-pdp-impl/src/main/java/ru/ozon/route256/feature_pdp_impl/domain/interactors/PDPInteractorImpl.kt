package ru.ozon.route256.feature_pdp_impl.domain.interactors

import ru.ozon.route256.feature_pdp_impl.domain.mapper.toUI
import ru.ozon.route256.feature_pdp_impl.domain.repository.PDPRepository
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductUI
import javax.inject.Inject

class PDPInteractorImpl @Inject constructor(
    private val rep: PDPRepository
) : PDPInteractor {
    override suspend fun getProductById(it: String): ProductUI? {
        return rep.getProductById(it)?.toUI()
    }

    override suspend fun getCartCount(guid: String?): Int {
        return rep.getCartCount(guid)
    }

    override suspend fun updateCountInCart(guid: String?, count: Int) {
        rep.updateCountInCart(guid, count)
    }
}