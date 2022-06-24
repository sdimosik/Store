package ru.ozon.route256.feature_products_impl.presentation.adapter

import com.bumptech.glide.RequestManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.ozon.route256.core_utils.ui.BaseDiffModel
import ru.ozon.route256.core_utils.ui.BaseDiffUtilItemCallback
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI

class ProductsAdapter(
    glide: RequestManager,
    onClick: (ProductInListUI) -> Unit
) : AsyncListDifferDelegationAdapter<BaseDiffModel>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainDelegates.productInListDelegate(glide, onClick))
    }
}