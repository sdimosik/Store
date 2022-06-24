package ru.ozon.route256.feature_products_impl.presentation.model

import ru.ozon.route256.core_utils.ui.BaseDiffModel

data class ProductInListUI(
    val guid: String,
    val image: String,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var countView: Int = 0
) : BaseDiffModel {
    override val id: Long
        get() = guid.hashCode().toLong()
}
