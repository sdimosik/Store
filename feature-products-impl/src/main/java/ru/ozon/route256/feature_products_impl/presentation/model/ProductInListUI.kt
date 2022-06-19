package ru.ozon.route256.feature_products_impl.presentation.model

data class ProductInListUI(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var countView: Int = 0
)
