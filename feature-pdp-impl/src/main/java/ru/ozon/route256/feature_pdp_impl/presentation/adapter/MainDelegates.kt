package ru.ozon.route256.feature_pdp_impl.presentation.adapter

import android.app.Activity
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.ozon.route256.core_utils.ui.BaseDiffModel
import ru.ozon.route256.feature_pdp_impl.databinding.ImageItemBinding
import ru.ozon.route256.feature_pdp_impl.presentation.model.ImageItem

object MainDelegates {

    fun imageDelegate(
        glide: RequestManager,
    ) = adapterDelegateViewBinding<ImageItem, BaseDiffModel, ImageItemBinding>(
        viewBinding = { layoutInflater, parent ->
            ImageItemBinding.inflate(layoutInflater, parent, false)
        }
    ) {

        bind {
            with(binding) {
                glide.load(item.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
            }
        }

        onViewRecycled {
            if ((binding.image.context as? Activity)?.isDestroyed?.not() == true) {
                glide.clear(binding.image)
            }
        }
    }
}