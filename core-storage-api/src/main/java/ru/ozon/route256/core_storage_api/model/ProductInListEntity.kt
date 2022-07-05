package ru.ozon.route256.core_storage_api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductInListEntity(
    val guid: String,
    val image: List<String>,
    val name: String,
    val price: String,
    val rating: Double,
    val isFavorite: Boolean,
    val isInCart: Boolean,
    var countView: Int = 0
) : Parcelable
