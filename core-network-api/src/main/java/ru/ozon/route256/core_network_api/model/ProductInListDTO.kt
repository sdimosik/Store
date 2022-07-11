package ru.ozon.route256.core_network_api.model

data class ProductInListDTO(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean
)
