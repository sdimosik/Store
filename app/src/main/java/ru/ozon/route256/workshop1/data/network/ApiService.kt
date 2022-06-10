package ru.ozon.route256.workshop1.data.network

import ProductData
import retrofit2.Response
import retrofit2.http.GET
import ru.ozon.route256.workshop1.data.model.ProductInListData

interface ApiService {

    @GET("50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
    suspend fun getProductsInList (): Response<List<ProductInListData>>

    @GET("8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
    suspend fun getProducts (): Response<List<ProductData>>
}