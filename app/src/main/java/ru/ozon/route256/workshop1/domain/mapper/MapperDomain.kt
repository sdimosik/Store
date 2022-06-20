package ru.ozon.route256.workshop1.domain.mapper

import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI
import ru.ozon.route256.workshop1.presentation.model.ProductUI

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

fun ProductInListDomain.toUI(): ProductInListUI = ProductInListUI(
    guid, image, name, price, rating, isFavorite, isInCart, countView
)

fun ProductDomain.toProductInListData(): ProductInListDomain = ProductInListDomain(
    guid, if (images.isNotEmpty()) images[0] else "", name, price, rating, isFavorite, isInCart
)