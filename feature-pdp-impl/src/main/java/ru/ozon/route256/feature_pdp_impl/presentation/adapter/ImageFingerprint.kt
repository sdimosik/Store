package ru.ozon.route256.feature_pdp_impl.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.ozon.route256.core_utils.ui.adapter.BaseViewHolder
import ru.ozon.route256.core_utils.ui.adapter.ItemFingerprint
import ru.ozon.route256.core_utils.ui.adapter.ListItem
import ru.ozon.route256.feature_pdp_impl.R
import ru.ozon.route256.feature_pdp_impl.databinding.ImageItemBinding
import ru.ozon.route256.feature_pdp_impl.presentation.model.ImageUI

class ImageFingerprint(
    private val glide: RequestManager
) : ItemFingerprint<ImageItemBinding, ImageUI> {

    override fun isRelativeItem(item: ListItem): Boolean = item is ImageUI

    override fun getLayoutId(): Int = R.layout.image_item

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ImageItemBinding, ImageUI> {
        val binding = ImageItemBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding, glide)
    }

    override fun getDiffUtil(): DiffUtil.ItemCallback<ImageUI> = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<ImageUI>() {

        override fun areItemsTheSame(oldItem: ImageUI, newItem: ImageUI) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ImageUI, newItem: ImageUI) =
            oldItem == newItem
    }
}

class ImageViewHolder(
    binding: ImageItemBinding,
    private val glide: RequestManager
) : BaseViewHolder<ImageItemBinding, ImageUI>(binding) {


    override fun onBind(item: ImageUI) {
        super.onBind(item)
        with(binding) {
            glide.load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
        }
    }

    override fun onViewRecycled() {
        if ((binding.image.context as? Activity)?.isDestroyed?.not() == true) {
            glide.clear(binding.image)
        }
    }
}