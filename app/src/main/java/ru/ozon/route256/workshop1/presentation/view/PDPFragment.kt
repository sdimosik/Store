package ru.ozon.route256.workshop1.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.PdpFragmentBinding
import ru.ozon.route256.workshop1.domain.di.ServiceLocatorDomain
import ru.ozon.route256.workshop1.presentation.base.BaseViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.PDPViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.viewModelCreator

class PDPFragment() : Fragment(R.layout.pdp_fragment) {

    companion object {
        const val PRODUCT_ID = "ru.ozon.route256.workshop1.presentation.view.productId"
    }

    private var currentId: String? = null

    val binding by viewBinding(PdpFragmentBinding::bind)

    private val glide by lazy {
        Glide.with(this)
    }

    private val viewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(ServiceLocatorDomain.provideProductsInteractor())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentId = arguments?.getString(PRODUCT_ID)
        viewModel.getProductById(currentId)

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
            binding.nameTV.text = it.name
            binding.priceTV.text = it.price

            glide.load(
                if (it.images.isNotEmpty()) it.images[0]
                else ""
            )
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.productIV)

            binding.ratingView.rating = it.rating.toFloat()
        }
    }

    override fun onPause() {
        setFragmentResult(ProductsFragment.REQUEST_ID_COUNT_ADD_KEY, bundleOf(ProductsFragment.BUNDLE_ID_COUNT_ADD_KEY to currentId))
        super.onPause()
    }
}