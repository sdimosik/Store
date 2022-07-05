package ru.ozon.route256.feature_products_impl.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import ru.ozon.route256.core_utils.ui.adapter.BaseViewHolder
import ru.ozon.route256.core_utils.ui.adapter.ItemFingerprint
import ru.ozon.route256.core_utils.ui.adapter.ListItem
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.HeaderListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.model.HeaderUI

class HeaderFingerprint : ItemFingerprint<HeaderListItemBinding, HeaderUI> {

    override fun isRelativeItem(item: ListItem): Boolean = item is HeaderUI

    override fun getLayoutId(): Int = R.layout.header_list_item

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<HeaderListItemBinding, HeaderUI> {
        val binding = HeaderListItemBinding.inflate(layoutInflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<HeaderUI> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<HeaderUI>() {
        override fun areItemsTheSame(oldItem: HeaderUI, newItem: HeaderUI) =
            oldItem.text.hashCode() == newItem.text.hashCode()

        override fun areContentsTheSame(oldItem: HeaderUI, newItem: HeaderUI) = oldItem == oldItem
    }
}

class HeaderViewHolder(
    binding: HeaderListItemBinding
) : BaseViewHolder<HeaderListItemBinding, HeaderUI>(binding) {

    override fun onBind(item: HeaderUI) {
        super.onBind(item)
        binding.text.text = item.text
    }
}