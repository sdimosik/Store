package ru.ozon.route256.feature_add_product_impl.presentation.view_model

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ozon.route256.core_utils.ui.BaseViewModel
import ru.ozon.route256.feature_add_product_impl.domain.interactors.AddProductInteractor
import ru.ozon.route256.feature_add_product_impl.presentation.mapper.toDomain
import ru.ozon.route256.feature_add_product_impl.presentation.model.AddProductUI
import javax.inject.Inject

class AddProductViewModel @Inject constructor(
    private val addProductInteractor: AddProductInteractor
) : BaseViewModel() {

    fun addProduct(newProduct: AddProductUI) {
        viewModelScope.launch {
            addProductInteractor.addProduct(newProduct.toDomain())
        }
    }
}