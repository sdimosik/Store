package ru.ozon.route256.core_network_impl.data

import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO
import ru.ozon.route256.core_network_impl.mock.productDTOs
import ru.ozon.route256.core_network_impl.mock.productInListDTOs
import javax.inject.Inject

class ProductApiImpl @Inject constructor(

) : ProductApi {
    override suspend fun getProductsInList(): List<ProductInListDTO> {
        return productInListDTOs
    }

    override suspend fun getProducts(): List<ProductDTO> {
        return productDTOs
    }
}