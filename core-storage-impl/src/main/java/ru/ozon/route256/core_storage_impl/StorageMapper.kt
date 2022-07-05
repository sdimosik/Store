package ru.ozon.route256.core_storage_impl

import ru.ozon.route256.core_storage_api.model.ProductEntity
import ru.ozon.route256.core_storage_api.model.ProductEntityList
import ru.ozon.route256.core_storage_api.model.ProductInListEntity
import ru.ozon.route256.core_storage_api.model.ProductInListEntityList

fun ProductInListEntityList.toList(): List<ProductInListEntity> {
    val list = mutableListOf<ProductInListEntity>()
    this.list.forEach {
        list.add(it)
    }
    return list
}

fun ProductEntityList.toList(): List<ProductEntity> {
    val list = mutableListOf<ProductEntity>()
    this.list.forEach {
        list.add(it)
    }
    return list
}

fun ProductEntity.toProductInListEntity(): ProductInListEntity = ProductInListEntity(
    guid, images, name, price, rating, isFavorite, isInCart
)