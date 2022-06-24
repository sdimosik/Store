package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import javax.inject.Inject

class PDPNavigationImpl @Inject constructor() : PDPNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is PDPFragment) {
            val navHost =
                fragment.activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainer)
            navHost?.childFragmentManager?.fragments?.find {
                it is PDPFragment
            } == null
        } else {
            true
        }
    }
}