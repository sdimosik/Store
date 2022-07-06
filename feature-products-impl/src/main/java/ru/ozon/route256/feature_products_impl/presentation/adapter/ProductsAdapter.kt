package ru.ozon.route256.feature_products_impl.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ozon.route256.core_utils.ui.adapter.FingerprintAdapter
import ru.ozon.route256.feature_products_impl.presentation.adapter.fingerprint.HeaderFingerprint
import ru.ozon.route256.feature_products_impl.presentation.adapter.fingerprint.ProductFingerprint
import ru.ozon.route256.feature_products_impl.presentation.adapter.fingerprint.ProductViewHolder
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

class ProductsAdapter(
    glide: RequestManager,
    private val sharedViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool(),
    onClick: (ProductInListUI) -> Unit,
) : FingerprintAdapter(
    listOf(
        HeaderFingerprint(),
        ProductFingerprint(glide, onClick, sharedViewPool)
    )
)