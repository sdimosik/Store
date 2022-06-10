package ru.ozon.route256.workshop1.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.FragmentProductsBinding
import ru.ozon.route256.workshop1.domain.di.ServiceLocatorDomain
import ru.ozon.route256.workshop1.presentation.base.BaseViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.ProductsViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.viewModelCreator

class ProductsFragment() : Fragment(R.layout.fragment_products) {

    companion object {
        const val REQUEST_ID_COUNT_ADD_KEY =
            "ru.ozon.route256.workshop1.presentation.view.REQUEST_COUNT_KEY"
        const val BUNDLE_ID_COUNT_ADD_KEY =
            "ru.ozon.route256.workshop1.presentation.view.BUNDLE_COUNT_KEY"
    }

    val binding by viewBinding(FragmentProductsBinding::bind)

        private val viewModel: ProductsViewModel by viewModelCreator {
            ProductsViewModel(ServiceLocatorDomain.provideProductsInteractor())
        }

    private val productsAdapter by lazy {
        ProductsAdapter(
            Glide.with(this)
        ) {
            findNavController().navigate(
                R.id.action_fragment_products_to_PDPFragment,
                bundleOf(PDPFragment.PRODUCT_ID to it.guid)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        setFragmentResultListener(REQUEST_ID_COUNT_ADD_KEY) { requestKey, bundle ->
            val id = bundle.getString(BUNDLE_ID_COUNT_ADD_KEY)
            val list = productsAdapter.getList()
            list.mapIndexed { _, productInListUI ->
                if (productInListUI.guid == id) {
                    productInListUI.countView += 1
                    return@mapIndexed
                }
            }
            productsAdapter.submitList(list)
        }
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
                findNavController().navigate(R.id.action_fragment_products_to_AddProductFragment)
            }
        }
        viewModel.action.observe(viewLifecycleOwner) { event ->
            when (event.getContentIfNotHandled()) {
                is BaseViewModel.Action.ShowToast -> {
                    val textRes = (event.peekContent() as BaseViewModel.Action.ShowToast).messageRes
                    Toast.makeText(requireContext(), textRes, Toast.LENGTH_SHORT)
                }
                else -> {

                }
            }
        }

        viewModel.productLD.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }
}