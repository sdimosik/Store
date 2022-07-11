package ru.ozon.route256.core_storage_impl

import ru.ozon.route256.core_storage_api.model.*

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

fun InCartGuidListEntity.toList(): List<InCartGuidEntity> {
    val list = mutableListOf<InCartGuidEntity>()
    this.list.forEach {
        list.add(it)
    }
    return list
}

fun ProductEntity.toProductInListEntity(): ProductInListEntity = ProductInListEntity(
    guid, images, name, price, rating, isFavorite, isInCart
)