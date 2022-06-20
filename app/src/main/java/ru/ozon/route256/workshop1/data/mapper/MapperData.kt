package ru.ozon.route256.workshop1.data.mapper

import ProductData
import ru.ozon.route256.workshop1.data.model.ProductInListData
import ru.ozon.route256.workshop1.domain.model.ProductDomain
import ru.ozon.route256.workshop1.domain.model.ProductInListDomain

fun ProductData.toDomain(): ProductDomain = ProductDomain(
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

fun ProductInListData.toDomain(): ProductInListDomain = ProductInListDomain(
    guid, image, name, price, rating, isFavorite, isInCart
)