package ru.ozon.route256.core_navigation_impl.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.ozon.route256.core_navigation_impl.R
import ru.ozon.route256.core_navigation_impl.di.FeatureInjectorProxy
import ru.ozon.route256.feature_pdp_impl.presentation.view.PDPFragment
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.presentation.view.ProductsFragment
import javax.inject.Inject

class ProductNavigationImpl @Inject constructor() : ProductNavigationApi {

    override fun navigateToPDP(fragment: Fragment, guid: String) {
        FeatureInjectorProxy.initFeaturePDPDI()
        fragment.findNavController().navigate(
            R.id.action_fragment_products_to_PDPFragment,
            bundleOf(PDPFragment.PRODUCT_ID to guid)
        )
    }

    override fun navigateToAddProduct(fragment: Fragment) {
        FeatureInjectorProxy.initFeatureAddProductDI()
        fragment.findNavController().navigate(R.id.action_fragment_products_to_AddProductFragment)
    }

    override fun isFeatureClosed(fragment: Fragment): Boolean {
        return if (fragment.javaClass.simpleName != ProductsFragment::class.simpleName) {
            fragment.activity?.supportFragmentManager?.findFragmentByTag(ProductsFragment::class.java.simpleName) == null
        } else {
            true
        }
    }
}