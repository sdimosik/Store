package ru.ozon.route256.core_network_impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ozon.route256.core_network_api.BuildConfig
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_network_impl.data.ProductApiImpl
import javax.inject.Singleton

@Module
abstract class NetworkModule {

//    @Singleton
//    @Provides
//    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.apply {
//            level = if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor.Level.BODY
//            } else HttpLoggingInterceptor.Level.NONE
//        }
//        return interceptor
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//        loggingInterceptor: HttpLoggingInterceptor,
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideNewsApiService(okHttpClient: OkHttpClient): ProductApi {
//        return Retrofit.Builder()
//            .baseUrl("https://run.mocky.io/v3/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//            .create(ProductApi::class.java)
//    }

    @Binds
    abstract fun bindProductApi(productApi: ProductApiImpl): ProductApi
}