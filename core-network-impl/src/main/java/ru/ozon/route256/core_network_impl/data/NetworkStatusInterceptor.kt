package ru.ozon.route256.core_network_impl.data

import okhttp3.Interceptor
import okhttp3.Response
import ru.ozon.route256.core_utils.NoConnectionException
import javax.inject.Inject

class NetworkStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NoConnectionException()
        }
    }
}