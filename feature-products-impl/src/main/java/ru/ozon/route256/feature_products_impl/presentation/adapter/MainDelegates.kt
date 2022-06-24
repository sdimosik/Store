package ru.ozon.route256.feature_products_impl.presentation.adapter

import android.app.Activity
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.core_utils.ui.BaseDiffModel
import ru.ozon.route256.feature_products_impl.databinding.ProductListItemBinding
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

object MainDelegates {

    fun productInListDelegate(
        glide: RequestManager,
        onClick: (ProductInListUI) -> Unit
    ) = adapterDelegateViewBinding<ProductInListUI, BaseDiffModel, ProductListItemBinding>(
        viewBinding = { layoutInflater, parent ->
            ProductListItemBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        binding.root.setOnClickListener {
            onClick(item)
        }

        bind {
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

        onViewRecycled {
            if ((binding.productIV.context as? Activity)?.isDestroyed?.not() == true) {
                glide.clear(binding.productIV)
            }
        }
    }
}