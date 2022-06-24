package ru.ozon.route256.feature_add_product_impl.presentation.mapper

import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI

fun AddProductUI.toDomain(): AddProductDomain =
    AddProductDomain(
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