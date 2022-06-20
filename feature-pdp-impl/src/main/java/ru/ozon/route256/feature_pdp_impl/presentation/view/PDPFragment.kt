package ru.ozon.route256.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.core_utils.viewModelCreator
import ru.ozon.route256.feature_pdp_api.PDPNavigationApi
import ru.ozon.route256.feature_pdp_impl.R
import ru.ozon.route256.feature_pdp_impl.databinding.FragmentPdpBinding
import ru.ozon.route256.feature_pdp_impl.di.PDPFeatureComponent
import ru.ozon.route256.feature_pdp_impl.domain.interactors.PDPInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.adapter.ImagesAdapter
import ru.ozon.route256.feature_pdp_impl.presentation.model.ImageItem
import ru.ozon.route256.feature_pdp_impl.presentation.view_model.PDPViewModel
import javax.inject.Inject

class PDPFragment() : Fragment(R.layout.fragment_pdp) {

    companion object {
        const val PRODUCT_ID = "ru.ozon.route256.workshop1.presentation.view.productId"
    }

    private var currentId: String? = null

    private val binding by viewBinding(FragmentPdpBinding::bind)

    private val glide by lazy {
        Glide.with(this)
    }

    @Inject
    lateinit var pdpNavigationApi: PDPNavigationApi

    @Inject
    lateinit var pdpInteractor: PDPInteractor

    private val viewModel: PDPViewModel by viewModelCreator {
        PDPViewModel(pdpInteractor)
    }

    private val imagesAdapter by lazy {
        ImagesAdapter(
            Glide.with(this)
        )
    }

    private val snapHelper by lazy {
        PagerSnapHelper()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PDPFeatureComponent.pdpFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imagesRV.adapter = imagesAdapter
        snapHelper.attachToRecyclerView(binding.imagesRV)

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

            imagesAdapter.items = ImageItem.toList(it.images)

            binding.ratingView.rating = it.rating.toFloat()
        }
    }

    override fun onPause() {
        if (isRemoving) {
            if (pdpNavigationApi.isFeatureClosed(this)) {
                PDPFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}