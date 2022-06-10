package ru.ozon.route256.workshop1.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ozon.route256.workshop1.BuildConfig
import ru.ozon.route256.workshop1.data.network.ApiService


// Подробнее можете почитать [тут](http://sergeyteplyakov.blogspot.com/2013/03/di-service-locator.html),
// [тут](https://habr.com/ru/post/465395/) и [тут](https://javatutor.net/articles/j2ee-pattern-service-locator).

object ServiceLocatorData {

    private val provideHTTPLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }

    private val provideOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(provideHTTPLoggingInterceptor)
        .build()

    val provideApiService = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient)
        .build()
        .create(ApiService::class.java)
}
