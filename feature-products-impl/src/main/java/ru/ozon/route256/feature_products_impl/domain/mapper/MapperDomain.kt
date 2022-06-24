package ru.ozon.route256.feature_products_impl.domain.mapper

import ru.ozon.route256.feature_products_impl.domain.model.ProductInListDomain
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

fun ProductInListDomain.toUI(): ProductInListUI = ProductInListUI(
    guid, image, name, price, rating, isFavorite, isInCart, countView
)