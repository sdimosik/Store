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

    fun getProductsList() {
        viewModelScope.launch(handlerException) {
            _productLD.postValue(productsInteractor.getProductsList())
        }
    }

    fun addCountView(id: String?) {
        viewModelScope.launch(handlerException) {
            productsInteractor.addCountView(id)
        }
    }
}