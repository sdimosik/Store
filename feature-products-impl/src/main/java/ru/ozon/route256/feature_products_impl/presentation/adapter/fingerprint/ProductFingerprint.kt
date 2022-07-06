package ru.ozon.route256.feature_products_impl.presentation.adapter.fingerprint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ozon.route256.core_utils.ui.adapter.BaseViewHolder
import ru.ozon.route256.core_utils.ui.adapter.ItemFingerprint
import ru.ozon.route256.core_utils.ui.adapter.ListItem
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.adapter.ImageAdapter
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

class ProductFingerprint(
    private val glide: RequestManager,
    private val onClick: (ProductInListUI) -> Unit,
    private val sharedViewPool: RecyclerView.RecycledViewPool
) : ItemFingerprint<ProductListItemBinding, ProductInListUI> {

    override fun isRelativeItem(item: ListItem): Boolean = item is ProductInListUI

    override fun getLayoutId(): Int = R.layout.product_list_item

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ProductListItemBinding, ProductInListUI> {
        val binding = ProductListItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding, glide, sharedViewPool, onClick)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<ProductInListUI> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ProductInListUI>() {

        override fun areItemsTheSame(oldItem: ProductInListUI, newItem: ProductInListUI) =
            oldItem.guid.hashCode() == newItem.guid.hashCode()

        override fun areContentsTheSame(oldItem: ProductInListUI, newItem: ProductInListUI) =
            oldItem == newItem

        override fun getChangePayload(oldItem: ProductInListUI, newItem: ProductInListUI): Any? {
            if (oldItem.countView != newItem.countView) return newItem.countView
            return super.getChangePayload(oldItem, newItem)
        }
    }
}

class ProductViewHolder(
    binding: ProductListItemBinding,
    private val glide: RequestManager,
    private val sharedViewPool: RecyclerView.RecycledViewPool,
    private val onClick: (ProductInListUI) -> Unit
) : BaseViewHolder<ProductListItemBinding, ProductInListUI>(binding) {

    private val imageAdapter = ImageAdapter(glide)
    private val snapHelper = PagerSnapHelper()

    init {
        binding.root.setOnClickListener {
            onClick(item)
        }

        binding.productRV.apply {
            adapter = imageAdapter
            setHasFixedSize(true)
            setRecycledViewPool(sharedViewPool)
        }

        snapHelper.attachToRecyclerView(binding.productRV)
    }

    override fun onBind(item: ProductInListUI) {
        super.onBind(item)
        with(binding) {
            nameTV.text = item.name
            priceTV.text = item.price
            ratingView.rating = item.rating.toFloat()
            countViewTV.text = item.countView.toString()
            imageAdapter.submitList(item.image)
        }
    }

    override fun onBind(item: ProductInListUI, payloads: List<Any>) {
        super.onBind(item, payloads)
        val countView = payloads.last() as Int
        binding.countViewTV.text = countView.toString()
    }
}