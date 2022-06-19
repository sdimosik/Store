package ru.ozon.route256.feature_products_impl.data.mapper

import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.core_storage_api.model.ProductInListEntity
import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain

fun ProductInListDTO.toDomain(): ProductInListDomain = ProductInListDomain(
    guid, image, name, price, rating, isFavorite, isInCart
)

fun ProductInListDTO.toEntity(): ProductInListEntity = ProductInListEntity(
    guid, image, name, price, rating, isFavorite, isInCart,
)

fun ProductInListDomain.toEntity(): ProductInListEntity = ProductInListEntity(
    guid, image, name, price, rating, isFavorite, isInCart, countView
)

fun ProductInListEntity.toDomain(): ProductInListDomain = ProductInListDomain(
    guid, image, name, price, rating, isFavorite, isInCart, countView
)