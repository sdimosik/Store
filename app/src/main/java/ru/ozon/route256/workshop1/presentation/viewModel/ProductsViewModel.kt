package ru.ozon.route256.workshop1.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.presentation.base.BaseViewModel
import ru.ozon.route256.workshop1.presentation.model.ProductInListUI

class ProductsViewModel(
    private val productsInteractor: ProductsInteractor
) : BaseViewModel() {

    private val _productLD = MutableLiveData<List<ProductInListUI>>()
    val productLD: LiveData<List<ProductInListUI>> = _productLD

    init {
        viewModelScope.launch(handlerException) {
            val list = productsInteractor.getProductsList()
            val detail =
            _productLD.postValue(productsInteractor.getProductsList())
        }
    }
}