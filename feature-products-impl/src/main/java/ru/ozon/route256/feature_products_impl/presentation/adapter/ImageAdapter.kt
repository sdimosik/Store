package ru.ozon.route256.feature_products_impl.presentation.adapter

import com.bumptech.glide.RequestManager
import ru.ozon.route256.core_utils.ui.adapter.FingerprintAdapter
import ru.ozon.route256.feature_products_impl.presentation.adapter.fingerprint.ImageFingerprint

class ImageAdapter(
    glide: RequestManager,
) : FingerprintAdapter(
    listOf(
        ImageFingerprint(glide)
    )
)