package com.example.adro.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.domain.models.ApiResult
import com.example.domain.models.ErrorResponse
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

object CommonFlowExtensions {


    fun <T> Flow<T>.handleErrors(): Flow<T> =
        catch { e -> Log.d("TAG", "handleErrors: ", e.fillInStackTrace()) }

    fun Exception.toCustomExceptions(): ApiResult<Error> = when (this) {
        is ClientRequestException -> {
            ApiResult.Error(ErrorResponse(message = message))
        }

        is ServerResponseException -> {
            ApiResult.Error(ErrorResponse(message = message))
        }

        is SocketTimeoutException -> {
            ApiResult.Error(ErrorResponse(message = message))
        }

        is UnknownHostException -> {
            ApiResult.Error(ErrorResponse(message = message))
        }

        is IOException -> {
            ApiResult.Error(ErrorResponse(message = message))
        }

        else -> {
            ApiResult.Error(ErrorResponse(message = message))
        }
    }

    inline fun <reified T> convertToFlow(
        crossinline call: suspend () -> HttpResponse,
        crossinline success: (data: T) -> Unit = {},
        crossinline failure: () -> Unit = {}
    ): Flow<ApiResult<T>> =
        flow<ApiResult<T>> {
            emit(ApiResult.Loading(true))
            try {
                val response = call()
                emit(ApiResult.Loading(false))
                when (response.status) {
                    HttpStatusCode.OK ->  emit(ApiResult.Success(response.body()))
                    HttpStatusCode.Unauthorized -> {}
                    HttpStatusCode.UnprocessableEntity -> emit(ApiResult.Error(ErrorResponse(message = response.status.value.toString())))
                    HttpStatusCode.BadGateway -> {}
                    HttpStatusCode.BadRequest -> {}
                    HttpStatusCode.InternalServerError -> {}
                    HttpStatusCode.MethodNotAllowed -> {}
                    HttpStatusCode.Forbidden -> {}
                    HttpStatusCode.NotFound -> {}
                    HttpStatusCode.RequestTimeout -> {}
                    else -> {}
                }
                success(response.body())
            } catch (e: Exception) {
                emit(ApiResult.Loading(false))
                emit(ApiResult.Error(ErrorResponse(message = e.message)))
                failure()
            }
        }.flowOn(Dispatchers.IO)


    @Composable
    fun <T> rememberFlow(
        flow: Flow<T>,
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    ): Flow<T> {
        return remember(
            key1 = flow,
            key2 = lifecycleOwner
        ) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
    }

    @Composable
    fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
        initial: R,
        context: CoroutineContext = EmptyCoroutineContext
    ): State<R> {
        val lifecycleAwareFlow = rememberFlow(flow = this)
        return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
    }

    @Suppress("StateFlowValueCalledInComposition")
    @Composable
    fun <T> StateFlow<T>.collectAsStateLifecycleAware(
        context: CoroutineContext = EmptyCoroutineContext
    ): State<T> = collectAsStateLifecycleAware(value, context)

}