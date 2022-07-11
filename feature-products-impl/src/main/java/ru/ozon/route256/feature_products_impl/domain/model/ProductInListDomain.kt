package ru.ozon.route256.feature_products_impl.domain.model

data class ProductInListDomain(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var countView: Int = 0
)
