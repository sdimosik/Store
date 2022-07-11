package ru.ozon.route256.feature_pdp_impl.presentation.model

import ru.ozon.route256.core_utils.ui.adapter.ListItem

data class ImageUI(
    val image: String
) : ListItem {

    val id: Int by lazy {
        image.hashCode()
    }

    companion object {
        fun toList(list: List<String>): List<ImageUI> {
            val new = mutableListOf<ImageUI>()
            list.forEach {
                new.add(ImageUI(it))
            }
            return new
        }
    }
}
