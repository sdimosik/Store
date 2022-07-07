package ru.ozon.route256.feature_products_impl.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.core_utils.ui.adapter.ListItem
import ru.ozon.route256.feature_products_impl.domain.interactors.ProductsInteractor
import ru.ozon.route256.feature_products_impl.presentation.model.HeaderUI
import ru.ozon.route256.feature_products_impl.presentation.model.ProductInListUI
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val productsInteractor: ProductsInteractor
) : BaseViewModel() {

    sealed class ProductState {
        object NeedToSyncWithCache : ProductState()
    }

    private val _productLD = MutableLiveData<List<ListItem>>()
    val productLD: LiveData<List<ListItem>> = _productLD

    private val _productState = MutableLiveData<ProductState>()
    val productState: LiveData<ProductState> = _productState

    val headersList = mutableListOf<HeaderUI>()

    fun loadContent(forceRefresh: Boolean): List<LiveData<WorkInfo>> {
        return productsInteractor.loadContent(forceRefresh)
    }

    fun getProductsList() {
        viewModelScope.launch(handlerException) {
            val list = productsInteractor.getProductsList()
            _productLD.postValue(sortingAndAddingHeaders(list))
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

    private fun sortingAndAddingHeaders(list: List<ProductInListUI>): List<ListItem> {
        var currentIndex = 0
        val newList = mutableListOf<ListItem>()
        list.sortedBy { it.priceNum }
            .forEachIndexed { idx, product ->
                if (idx == 0 || (product.priceNum > headersList[currentIndex - 1].condition
                            && currentIndex < headersList.size)
                ) {
                    newList.apply {
                        add(headersList[currentIndex])
                        currentIndex++
                    }
                }
                newList.add(product)
            }
        return newList
    }

    fun addOrRemoveInCart(product: ProductInListUI) {
        viewModelScope.launch(handlerException) {
            productsInteractor.updateProductItemInList(product)
            _productState.postValue(ProductState.NeedToSyncWithCache)
        }
    }


}