package ru.ozon.route256.core_storage_api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInListEntityList(
    val list: List<ProductInListEntity>
) : Parcelable
