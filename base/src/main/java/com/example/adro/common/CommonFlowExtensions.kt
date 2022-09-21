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
import com.example.adro.base.ApiResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext


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
            ApiResult.Error(message)
        }
        is ServerResponseException -> {
            ApiResult.Error(message)
        }
        is SocketTimeoutException -> {
            ApiResult.Error(message.toString())
        }
        is UnknownHostException -> {
            ApiResult.Error(message.toString())
        }
        is IOException -> {
            ApiResult.Error(message.toString())
        }
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
                emit(ApiResult.Loading(false))
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
                    is UnknownHostException -> {
                        emit(ApiResult.Error(e.message.toString()))
                    }
                    is IOException -> {
                        emit(ApiResult.Error(e.message.toString()))
                    }
                    else -> {
                        emit(ApiResult.Error(e.message.toString()))
                    }
                }
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