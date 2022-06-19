package ru.ozon.route256.feature_pdp_impl.data.mapper

import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.feature_pdp_impl.domain.model.ProductDomain

fun ProductDTO.toDomain(): ProductDomain = ProductDomain(
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