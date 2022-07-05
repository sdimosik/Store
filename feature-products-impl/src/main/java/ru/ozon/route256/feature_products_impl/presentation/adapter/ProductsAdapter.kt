package ru.ozon.route256.feature_products_impl.presentation.adapter

import com.bumptech.glide.RequestManager
import ru.ozon.route256.core_utils.ui.adapter.FingerprintAdapter
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

class ProductsAdapter(
    glide: RequestManager,
    onClick: (ProductInListUI) -> Unit
) : FingerprintAdapter(
    listOf(
        HeaderFingerprint(),
        ProductFingerprint(glide, onClick)
    )
)