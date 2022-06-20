package ru.ozon.route256.workshop1.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.databinding.FragmentAddProductBinding
import ru.ozon.route256.workshop1.domain.di.ServiceLocatorDomain
import ru.ozon.route256.workshop1.presentation.model.ProductUI
import ru.ozon.route256.workshop1.presentation.viewModel.AddProductViewModel
import ru.ozon.route256.workshop1.presentation.viewModel.viewModelCreator

class AddProductFragment(

) : Fragment(R.layout.fragment_add_product){

    val binding by viewBinding(FragmentAddProductBinding::bind)

    private val viewModel: AddProductViewModel by viewModelCreator {
        AddProductViewModel(ServiceLocatorDomain.provideProductsInteractor())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            addBtn.setOnClickListener {

                val newProduct = ProductUI(
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

                requireActivity().onBackPressed()
            }
        }
    }
}