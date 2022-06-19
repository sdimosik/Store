package ru.ozon.route256.feature_products_impl.presentation.mapper

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

fun ProductInListUI.toUI(): ProductInListDomain = ProductInListDomain(
    guid, image, name, price, rating, isFavorite, isInCart, countView
)