package ru.ozon.route256.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) : BaseViewModel() {

    sealed class ProductState {
        data class IsLoading(val isLoading: Boolean) : ProductState()
    }

    private val _productLD = MutableLiveData<List<ProductInListUI>>()
    val productLD: LiveData<List<ProductInListUI>> = _productLD

    private val _productState = MutableLiveData<ProductState>()
    val productState: LiveData<ProductState> = _productState

    fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>> {
        return productsInteractor.loadContent(forceRefresh)
    }

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

    fun setAlive() {
        _state.postValue(State.Alive)
    }

    private fun setLoading(isLoading: Boolean) {
        _productState.postValue(ProductState.IsLoading(isLoading))
    }
}