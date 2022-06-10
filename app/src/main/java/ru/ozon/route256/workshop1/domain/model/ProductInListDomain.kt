package ru.ozon.route256.workshop1.domain.model

data class ProductInListDomain(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
)
