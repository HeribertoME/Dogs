package com.heriberto.dogswelove.data.datasource.remote.network.config

import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Response<T> {
    return try {
        apiCall()
    } catch (e: Exception) {
        e.printStackTrace()
        Response.error(500, "Unexpected error".toResponseBody(null))
    }
}

fun <T> Response<T>.getResultOrThrow(): T {
    if (isSuccessful && body() != null) {
        return body()!!
    } else {
        throw HttpException(this)
    }
}

fun <T> validateResponseError(response: Response<T>) {
    if (!response.isSuccessful) {
        throw HttpException(response)
    }
}