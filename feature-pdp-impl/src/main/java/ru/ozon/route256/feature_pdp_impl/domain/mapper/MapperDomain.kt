package ru.ozon.route256.feature_pdp_impl.domain.mapper

import ru.ozon.route256.feature_pdp_impl.domain.model.ProductDomain
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductUI


fun ProductDomain.toUI(): ProductUI = ProductUI(
    guid,
    name,
    price,
    description,
    rating,
    isFavorite,
    isInCart,
    images,
    weight,
    count,
    availableCount,
    additionalParams
)