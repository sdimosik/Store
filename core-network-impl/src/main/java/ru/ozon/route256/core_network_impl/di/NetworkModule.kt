package ru.ozon.route256.core_network_impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ozon.route256.core_network_api.BuildConfig
import ru.ozon.route256.core_network_api.ProductApi
import ru.ozon.route256.core_network_impl.data.ConnectionManager
import ru.ozon.route256.core_network_impl.data.NetworkStatusInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideConnectionManager(context: Context): ConnectionManager = ConnectionManager(context)

    @Singleton
    @Provides
    fun provideNetworkStatusInterceptor(connectionManager: ConnectionManager): Interceptor =
        NetworkStatusInterceptor(connectionManager)

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkStatusInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(okHttpClient: OkHttpClient): ProductApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProductApi::class.java)
    }
}