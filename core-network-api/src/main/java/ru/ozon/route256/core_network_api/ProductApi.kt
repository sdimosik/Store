package ru.ozon.route256.core_network_api

import retrofit2.Response
import retrofit2.http.GET
import ru.ozon.route256.core_network_api.model.ProductDTO
import ru.ozon.route256.core_network_api.model.ProductInListDTO

interface ProductApi {

    @GET("ee6876a1-8c02-45aa-bde4-b91817a8b210")
    suspend fun getProductsInList(): Response<List<ProductInListDTO>>

    @GET("d1b4763b-a5ea-471f-83bf-796da466e3d8")
    suspend fun getProducts(): Response<List<ProductDTO>>
}