package ru.ozon.route256.feature_add_product_impl.data.mapper

import ru.ozon.route256.core_storage_api.model.ProductEntity
import ru.ozon.route256.feature_add_product_impl.domain.model.AddProductDomain

fun AddProductDomain.toEntity(): ProductEntity =
    ProductEntity(
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
        additionalParams,
    )