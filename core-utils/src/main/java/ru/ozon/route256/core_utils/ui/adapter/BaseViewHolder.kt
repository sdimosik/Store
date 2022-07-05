package ru.ozon.route256.core_utils.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<out V : ViewBinding, I : ListItem>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {

    protected lateinit var item: I

    open fun onBind(item: I) {
        this.item = item
    }

    open fun onViewRecycled() {}
}