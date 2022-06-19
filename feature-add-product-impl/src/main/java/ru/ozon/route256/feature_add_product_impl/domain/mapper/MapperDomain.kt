package ru.ozon.route256.feature_add_product_impl.domain.mapper

import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI

fun AddProductDomain.toUI(): AddProductUI =
    AddProductUI(
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