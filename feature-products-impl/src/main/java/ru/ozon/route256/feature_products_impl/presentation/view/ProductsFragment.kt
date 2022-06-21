package ru.ozon.route256.feature_products_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.core_utils.viewModelCreator
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.FragmentProductsBinding
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.adapter.ProductsAdapter
import ru.ozon.route256.feature_products_impl.presentation.view_model.ProductsViewModel
import javax.inject.Inject

class ProductsFragment() : Fragment(R.layout.fragment_products) {

    private val binding by viewBinding(FragmentProductsBinding::bind)

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    @Inject
    lateinit var productsInteractor: ProductsInteractor

    private val viewModel: ProductsViewModel by viewModelCreator {
        ProductsViewModel(productsInteractor)
    }

    private val productsAdapter by lazy {
        ProductsAdapter(
            Glide.with(this)
        ) {
            viewModel.addCountView(it.guid)
            productNavigationApi.navigateToPDP(this, it.guid)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            frgProductsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    // if the recycler view is scrolled
                    // above hide the FAB
                    if (dy > 10 && btnAddProduct.isShown) {
                        btnAddProduct.hide()
                    }
                    // if the recycler view is
                    // scrolled above show the FAB
                    if (dy < -10 && !btnAddProduct.isShown) {
                        btnAddProduct.show()
                    }
                    // of the recycler view is at the first
                    // item always show the FAB
                    if (!recyclerView.canScrollVertically(-1)) {
                        btnAddProduct.show()
                    }
                }
            })

            frgProductsRecycler.adapter = productsAdapter

            btnAddProduct.setOnClickListener {
                productNavigationApi.navigateToAddProduct(this@ProductsFragment)
            }
        }
        viewModel.action.observe(viewLifecycleOwner) { event ->
            when (event.getContentIfNotHandled()) {
                is BaseViewModel.Action.ShowToast -> {
                    val textRes = (event.peekContent() as BaseViewModel.Action.ShowToast).messageRes
                    Toast.makeText(requireContext(), textRes, Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }

        viewModel.productLD.observe(viewLifecycleOwner) {
            productsAdapter.items = it
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProductsList()
    }

    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}