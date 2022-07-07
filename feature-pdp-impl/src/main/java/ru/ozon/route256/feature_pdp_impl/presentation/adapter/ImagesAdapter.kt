package ru.ozon.route256.feature_pdp_impl.presentation.adapter

import com.bumptech.glide.RequestManager
import ru.ozon.route256.core_utils.ui.adapter.FingerprintAdapter

class ImagesAdapter(
    glide: RequestManager
) : FingerprintAdapter(
    listOf(
        ImageFingerprint(glide)
    )
)