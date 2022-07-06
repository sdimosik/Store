package ru.ozon.route256.feature_products_impl.presentation.mapper

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

fun ProductInListUI.toDomain(): ProductInListDomain = ProductInListDomain(
    guid,
    image.map {
        it.image
    },
    name,
    price,
    rating,
    isFavorite,
    isInCart,
    countView
)