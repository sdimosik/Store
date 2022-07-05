package ru.ozon.route256.feature_products_impl.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.ozon.route256.core_utils.ui.adapter.BaseViewHolder
import ru.ozon.route256.core_utils.ui.adapter.ItemFingerprint
import ru.ozon.route256.core_utils.ui.adapter.ListItem
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

class ProductFingerprint(
    private val glide: RequestManager,
    private val onClick: (ProductInListUI) -> Unit
) : ItemFingerprint<ProductListItemBinding, ProductInListUI> {

    override fun isRelativeItem(item: ListItem): Boolean = item is ProductInListUI

    override fun getLayoutId(): Int = R.layout.product_list_item

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ProductListItemBinding, ProductInListUI> {
        val binding = ProductListItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding, glide, onClick)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<ProductInListUI> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ProductInListUI>() {

        override fun areItemsTheSame(oldItem: ProductInListUI, newItem: ProductInListUI) =
            oldItem.guid.hashCode() == newItem.guid.hashCode()

        override fun areContentsTheSame(oldItem: ProductInListUI, newItem: ProductInListUI) =
            oldItem == newItem
    }
}

class ProductViewHolder(
    binding: ProductListItemBinding,
    private val glide: RequestManager,
    private val onClick: (ProductInListUI) -> Unit
) : BaseViewHolder<ProductListItemBinding, ProductInListUI>(binding) {

    init {
        binding.root.setOnClickListener {
            onClick(item)
        }
    }

    override fun onBind(item: ProductInListUI) {
        super.onBind(item)
        with(binding) {
            glide.load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(productIV)

            nameTV.text = item.name
            priceTV.text = item.price
            ratingView.rating = item.rating.toFloat()
            countViewTV.text = item.countView.toString()
        }
    }

    override fun onViewRecycled() {
        if ((binding.productIV.context as? Activity)?.isDestroyed?.not() == true) {
            glide.clear(binding.productIV)
        }
    }
}