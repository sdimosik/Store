package ru.ozon.route256.core_utils.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

interface ItemFingerprint<V : ViewBinding, I : ListItem> {

    fun isRelativeItem(item: ListItem): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<V, I>

    fun getDiffUtil(): DiffUtil.ItemCallback<I>

}