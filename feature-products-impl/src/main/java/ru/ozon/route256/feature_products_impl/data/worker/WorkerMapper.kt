package ru.ozon.route256.worker_impl.data

import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.core_storage_api.model.ProductEntity
import ru.ozon.route256.core_storage_api.model.ProductInListEntity

fun ProductInListDTO.toEntity(): ProductInListEntity = ProductInListEntity(
    guid, image, name, price, rating, isFavorite, isInCart,
)

fun ProductDTO.toEntity(): ProductEntity = ProductEntity(
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