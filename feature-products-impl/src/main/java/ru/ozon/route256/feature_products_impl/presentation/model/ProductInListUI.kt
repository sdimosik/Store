package ru.ozon.route256.feature_products_impl.presentation.model

import ru.ozon.route256.core_utils.ui.adapter.ListItem

data class ProductInListUI(
    val guid: String,
    val image: List<ImageUI>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    var isInCart: Boolean,
    var countView: Int = 0
) : ListItem {

    val priceNum: Int by lazy {
        Integer.parseInt(price)
    }
}
