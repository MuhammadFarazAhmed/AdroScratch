package com.example.sharedcode.common

import io.github.aakira.napier.Napier
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


object CommonFlowExtensions {
    
    
    fun <T> Flow<T>.handleErrors(): Flow<T> = catch { e ->  Napier.e("Napier Error", e) }
    
    fun Exception.toCustomExceptions(): ApiResult<Error> = when (this) {
        is ClientRequestException -> {
            ApiResult.Error(message)
        }
        is ServerResponseException -> {
            ApiResult.Error(message)
        }
//        is SocketTimeoutException -> {
//            ApiResult.Error(message.toString())
//        }
//        is UnknownHostException -> {
//            ApiResult.Error(message.toString())
//        }
//        is IOException -> {
//            ApiResult.Error(message.toString())
//        }
        else -> {
            ApiResult.Error(message.toString())
        }
    }
    
    inline fun <reified T> convertToFlow(crossinline call: suspend () -> HttpResponse): Flow<ApiResult<T>> =
            flow<ApiResult<T>> {
                emit(ApiResult.Loading(true))
                try {
                    val response = call()
                    emit(ApiResult.Loading(false))
                    emit(ApiResult.Success(response.body()))
                } catch (e: Exception) {
                   // emit(ApiResult.Loading(false))
                    Napier.e("Napier Error", e)
                    when (e) {
                        is ClientRequestException -> {
                            emit(ApiResult.Error(e.message))
                        }
                        is ServerResponseException -> {
                            emit(ApiResult.Error(e.message))
                        }
                        is SocketTimeoutException -> {
                            emit(ApiResult.Error(e.message.toString()))
                        }
                        is IOException -> {
                            emit(ApiResult.Error(e.message.toString()))
                        }
                        else -> {
                           // emit(ApiResult.Error(e.message.toString()))
                        }
                    }
                }
            }.flowOn(Dispatchers.Main)
    
    
}