package ru.ozon.route256.feature_products_impl.presentation.model

import ru.ozon.route256.core_utils.ui.adapter.ListItem

data class HeaderUI(
    val text: String,
    val condition: Int
) : ListItem
