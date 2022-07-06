package ru.ozon.route256.feature_products_impl.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.feature_products_api.ProductNavigationApi
import ru.ozon.route256.feature_products_impl.R
import ru.ozon.route256.feature_products_impl.databinding.FragmentProductsBinding
import ru.ozon.route256.feature_products_impl.di.ProductFeatureComponent
import ru.ozon.route256.feature_products_impl.presentation.adapter.ProductsAdapter
import ru.ozon.route256.feature_products_impl.presentation.model.HeaderUI
import ru.ozon.route256.feature_products_impl.presentation.view_model.ProductsViewModel
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private val binding by viewBinding(FragmentProductsBinding::bind)

    init {
        ProductFeatureComponent.productFeatureComponent?.inject(this)
    }

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ProductsViewModel by viewModels {
        viewModelFactory
    }

    private val productsAdapter by lazy {
        ProductsAdapter(
            Glide.with(this)
        ) {
            viewModel.addCountView(it.guid)
            productNavigationApi.navigateToPDP(this, it.guid)
        }
    }

    private fun refreshData(forceRefresh: Boolean) {
        val list = viewModel.loadContent(forceRefresh)
        list.forEachIndexed { index, liveData ->
            liveData.observe(viewLifecycleOwner) {
                if (index == list.size - 1) {
                    if (it.state == WorkInfo.State.SUCCEEDED) {
                        viewModel.getProductsList()
                    }
                    if (it.state == WorkInfo.State.SUCCEEDED || it.state == WorkInfo.State.FAILED) {
                        binding.swipeContainer.isRefreshing = false
                        if (viewModel.state.value == BaseViewModel.State.Init) {
                            viewModel.setAlive()
                        }
                    }
                } else if (index == 0) {
                    if (it.state == WorkInfo.State.RUNNING) {
                        binding.swipeContainer.isRefreshing = true
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FIRST", "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            swipeContainer.setOnRefreshListener {
                refreshData(true)
            }

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

            frgProductsRecycler.apply {
                adapter = productsAdapter
                setItemViewCacheSize(30)
            }

            btnAddProduct.setOnClickListener {
                productNavigationApi.navigateToAddProduct(this@ProductsFragment)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    val isForce = viewModel.state.value != BaseViewModel.State.Init
                    refreshData(isForce)
                    Log.d("FIRST", "repeat update")
                    delay(10.seconds)
                }
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is BaseViewModel.State.Init -> {
                    viewModel.headersList.add(HeaderUI(getString(R.string.low_price), 100))
                    viewModel.headersList.add(
                        HeaderUI(
                            getString(R.string.default_price),
                            999999999
                        )
                    )
                }
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
            productsAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FIRST", "onResume")
        viewModel.getProductsList()
    }

    override fun onPause() {
        super.onPause()
        Log.d("FIRST", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FIRST", "onStop")
    }

    override fun onDestroy() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onDestroy()
        Log.d("FIRST", "onDestroy")
    }
}