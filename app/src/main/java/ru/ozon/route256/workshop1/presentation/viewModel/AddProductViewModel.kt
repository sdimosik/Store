package ru.ozon.route256.workshop1.presentation.viewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.presentation.base.BaseViewModel
import ru.ozon.route256.workshop1.presentation.mapper.toDomain
import ru.ozon.route256.workshop1.presentation.model.ProductUI

class AddProductViewModel(
    private val productsInteractor: ProductsInteractor
): BaseViewModel() {

    fun addProduct(newProduct: ProductUI) {
        viewModelScope.launch {
            productsInteractor.addProduct(newProduct.toDomain())
        }
    }

}