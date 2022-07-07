package ru.ozon.route256.feature_pdp_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.feature_pdp_impl.domain.interactors.PDPInteractor
import ru.ozon.route256.feature_pdp_impl.presentation.model.ProductUI
import javax.inject.Inject


class PDPViewModel @Inject constructor(
    private val pdpInteractor: PDPInteractor
) : BaseViewModel() {

    private val _productLD = MutableLiveData<ProductUI>()
    val productLD: LiveData<ProductUI> = _productLD

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> = _count

    fun getProductById(guid: String?) {
        viewModelScope.launch(handlerException) {
            _productLD.postValue(guid?.let {
                pdpInteractor.getProductById(it)
            })
        }
    }

    fun getCartCount(guid: String?) {
        viewModelScope.launch(handlerException) {
            _count.postValue(
                pdpInteractor.getCartCount(guid)
            )
        }
    }

    fun updateCountInCart(guid: String?, count: Int) {
        viewModelScope.launch(handlerException) {
            pdpInteractor.updateCountInCart(guid, count)
        }
    }
}