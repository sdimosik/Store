package ru.ozon.route256.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
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