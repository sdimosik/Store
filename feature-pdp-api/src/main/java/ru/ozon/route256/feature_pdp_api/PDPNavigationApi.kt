package ru.ozon.route256.feature_pdp_api

import androidx.fragment.app.Fragment

interface PDPNavigationApi {

    fun isFeatureClosed(fragment: Fragment): Boolean
}