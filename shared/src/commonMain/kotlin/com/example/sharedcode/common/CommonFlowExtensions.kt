package com.example.sharedcode.common

import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


//object HexToJetpackColor {
//    fun getColor(colorString: String): Color {
//        return Color(android.graphics.Color.parseColor("#$colorString"))
//    }
//}


object CommonFlowExtensions {


    fun <T> Flow<T>.handleErrors(): Flow<T> =
        catch { e ->  }
//
//    fun Exception.toCustomExceptions(): ApiResult<Error> = when (this) {
//        is ClientRequestException -> {
//            ApiResult.Error(message)
//        }
//        is ServerResponseException -> {
//            ApiResult.Error(message)
//        }
////        is SocketTimeoutException -> {
////            ApiResult.Error(message.toString())
////        }
////        is UnknownHostException -> {
////            ApiResult.Error(message.toString())
////        }
////        is IOException -> {
////            ApiResult.Error(message.toString())
////        }
//        else -> {
//            ApiResult.Error(message.toString())
//        }
//    }
//
    inline fun <reified T> convertToFlow(crossinline call: suspend () -> HttpResponse): Flow<ApiResult<T>> =
        flow<ApiResult<T>> {
            emit(ApiResult.Loading(true))
            try {
                val response = call()
                emit(ApiResult.Loading(false))
                emit(ApiResult.Success(response.body()))
            } catch (e: Exception) {
                emit(ApiResult.Loading(false))
                when (e) {
//                    is ClientRequestException -> {
//                        emit(ApiResult.Error(e.message))
//                    }
//                    is ServerResponseException -> {
//                        emit(ApiResult.Error(e.message))
//                    }
//                    is SocketTimeoutException -> {
//                        emit(ApiResult.Error(e.message.toString()))
//                    }
//                    is UnknownHostException -> {
//                        emit(ApiResult.Error(e.message.toString()))
//                    }
//                    is IOException -> {
//                        emit(ApiResult.Error(e.message.toString()))
//                    }
                    else -> {
                        emit(ApiResult.Error(e.message.toString()))
                    }
                }
            }
        }.flowOn(Dispatchers.Main)
//
//
//    @Composable
//    fun <T> rememberFlow(
//        flow: Flow<T>,
//        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
//    ): Flow<T> {
//        return remember(
//            key1 = flow,
//            key2 = lifecycleOwner
//        ) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
//    }
//
//    @Composable
//    fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
//        initial: R,
//        context: CoroutineContext = EmptyCoroutineContext
//    ): State<R> {
//        val lifecycleAwareFlow = rememberFlow(flow = this)
//        return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
//    }
//
//    @Suppress("StateFlowValueCalledInComposition")
//    @Composable
//    fun <T> StateFlow<T>.collectAsStateLifecycleAware(
//        context: CoroutineContext = EmptyCoroutineContext
//    ): State<T> = collectAsStateLifecycleAware(value, context)

}