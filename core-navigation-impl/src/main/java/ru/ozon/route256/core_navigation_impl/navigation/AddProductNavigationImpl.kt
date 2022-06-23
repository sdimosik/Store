package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_add_product_impl.presentation.view.AddProductFragment
import javax.inject.Inject

class AddProductNavigationImpl @Inject constructor() : AddProductNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment is AddProductFragment) {
            val navHost =
                fragment.activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainer)
            navHost?.childFragmentManager?.fragments?.find {
                it is AddProductFragment
            } == null
        } else {
            true
        }
    }
}