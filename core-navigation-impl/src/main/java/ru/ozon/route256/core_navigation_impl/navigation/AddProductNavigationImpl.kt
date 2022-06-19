package ru.ozon.route256.core_navigation_impl.navigation

import androidx.fragment.app.Fragment
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_add_product_impl.presentation.view.AddProductFragment
import javax.inject.Inject

class AddProductNavigationImpl @Inject constructor() : AddProductNavigationApi {
    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != AddProductFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(AddProductFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}