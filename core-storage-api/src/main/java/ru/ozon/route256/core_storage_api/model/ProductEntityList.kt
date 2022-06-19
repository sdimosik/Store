package ru.ozon.route256.core_storage_api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntityList(
    val list: List<ProductEntity>
) : Parcelable
