package ru.ozon.route256.core_utils

import retrofit2.Response

object NetUtil {

    suspend fun <R> get(
        call: suspend () -> Response<R>,
    ): Outcome<R> {
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Outcome.Success(body)
                }
            }

            return when (response.code()) {
                in 400..499 -> Outcome.Failure(ClientException())
                in 500..599 -> Outcome.Failure(ServerException())
                else -> Outcome.Failure(UncheckedException())
            }
        } catch (e: Exception) {
            return Outcome.Failure(e)
        }
    }
}