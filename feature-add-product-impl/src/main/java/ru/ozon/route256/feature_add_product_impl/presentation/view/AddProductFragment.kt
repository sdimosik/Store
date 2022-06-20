package ru.ozon.route256.feature_add_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.core_utils.viewModelCreator
import ru.ozon.route256.feature_add_product_api.AddProductNavigationApi
import ru.ozon.route256.feature_add_product_impl.R
import ru.ozon.route256.feature_add_product_impl.databinding.FragmentAddProductBinding
import ru.ozon.route256.feature_add_product_impl.di.AddProductFeatureComponent
import ru.ozon.route256.feature_add_product_impl.domain.interactors.AddProductInteractor
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI
import ru.ozon.route256.feature_add_product_impl.presentation.view_model.AddProductViewModel
import javax.inject.Inject

class AddProductFragment(

) : Fragment(R.layout.fragment_add_product) {

    private val binding by viewBinding(FragmentAddProductBinding::bind)

    private val viewModel: AddProductViewModel by viewModelCreator {
        AddProductViewModel(addProductInteractor)
    }

    @Inject
    lateinit var addProductNavigationApi: AddProductNavigationApi

    @Inject
    lateinit var addProductInteractor: AddProductInteractor

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AddProductFeatureComponent.addProductFeatureComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            addBtn.setOnClickListener {

                val newProduct = AddProductUI(
                    guid = guidET.text.toString(),
                    name = nameET.text.toString(),
                    price = priceET.text.toString(),
                    description = descriptionET.text.toString(),
                    rating = rating.rating.toDouble(),
                    isFavorite = isFavoriteSwitch.isActivated,
                    isInCart = isInCartSwitch.isActivated,
                    images = mutableListOf(imagesUrlET.text.toString()),
                    weight = weightET.text.toString().toDoubleOrNull(),
                    count = countET.text.toString().toIntOrNull(),
                    availableCount = availableCount.text.toString().toIntOrNull(),
                    additionalParams = hashMapOf()
                )

                viewModel.addProduct(newProduct)
            }
        }
    }

    override fun onPause() {
        if (isRemoving){
            if (addProductNavigationApi.isFeatureClosed(this)){
                AddProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }
}