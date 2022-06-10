package ru.ozon.route256.workshop1.presentation.mapper

import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI
import ru.ozon.route256.workshop1.presentation.model.ProductUI

fun ProductUI.toDomain(): ProductDomain = ProductDomain(
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

fun ProductInListUI.toUI(): ProductInListDomain = ProductInListDomain(
    guid, image, name, price, rating, isFavorite, isInCart
)