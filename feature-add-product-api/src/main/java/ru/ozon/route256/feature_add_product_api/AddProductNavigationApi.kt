package ru.ozon.route256.feature_add_product_api

import androidx.fragment.app.Fragment

interface AddProductNavigationApi {
    fun isFeatureClosed(fragment: Fragment): Boolean
}