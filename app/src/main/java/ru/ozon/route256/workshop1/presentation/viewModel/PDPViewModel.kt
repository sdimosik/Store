package ru.ozon.route256.workshop1.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.workshop1.domain.interactors.ProductsInteractor
import ru.ozon.route256.workshop1.presentation.base.BaseViewModel
import ru.ozon.route256.workshop1.presentation.model.ProductUI

class PDPViewModel(
    private val productsInteractor: ProductsInteractor
) : BaseViewModel() {

    private val _productLD = MutableLiveData<ProductUI>()
    val productLD: LiveData<ProductUI> = _productLD

    fun getProductById(guid: String?) {
        viewModelScope.launch(handlerException) {
            _productLD.postValue(guid?.let {
                productsInteractor.getProductById(it)
            })
        }
    }
}