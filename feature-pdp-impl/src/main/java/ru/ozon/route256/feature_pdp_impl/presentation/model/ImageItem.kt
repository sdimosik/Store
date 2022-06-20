package ru.ozon.route256.feature_pdp_impl.presentation.model

import ru.ozon.route256.core_utils.ui.BaseDiffModel

data class ImageItem(
    val image: String
) : BaseDiffModel {
    override val id: Long
        get() = image.hashCode().toLong()

    companion object {
        fun toList(list: List<String>): List<ImageItem> {
            val new = mutableListOf<ImageItem>()
            list.forEach {
                new.add(ImageItem(it))
            }
            return new
        }
    }
}
