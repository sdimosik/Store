package ru.ozon.route256.feature_pdp_impl.presentation.adapter

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ozon.route256.core_utils.ui.BaseDiffModel
import ru.ozon.route256.core_utils.ui.BaseDiffUtilItemCallback

class ImagesAdapter(
    glide: RequestManager
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainDelegates.imageDelegate(glide))
    }
}