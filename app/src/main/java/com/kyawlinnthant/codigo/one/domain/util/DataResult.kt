package com.kyawlinnthant.codigo.one.domain.util

import retrofit2.Response
import java.net.SocketTimeoutException

sealed class DataResult<out T>(
    open val data: T? = null
) {
    data class Success<out T>(override val data: T) : DataResult<T>()
    data class Failed(val error: String) : DataResult<Nothing>()
}

inline fun <reified T> safeApiCall(
    apiCall: () -> Response<T>
): DataResult<T> {
    return try {
        val response = apiCall()
        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            DataResult.Success(data = body!!)
        } else {
            // 4x,5x
            DataResult.Failed(error = response.message())
        }
    } catch (e: SocketTimeoutException) {
        DataResult.Failed(error = e.message ?: "Something went wrong")
        // you can use correct exception you want to catch
    } catch (e: Exception) {
        DataResult.Failed(error = e.message ?: "Something went wrong")
    }
}