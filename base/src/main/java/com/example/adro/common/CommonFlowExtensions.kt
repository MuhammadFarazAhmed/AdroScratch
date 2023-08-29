package com.example.adro.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.example.domain.models.ApiResult
import com.example.domain.models.ErrorResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Locale
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

    fun Exception.toCustomExceptions(): com.example.domain.models.ApiResult<Error> = when (this) {
        is ClientRequestException -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }

        is ServerResponseException -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }

        is SocketTimeoutException -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }

        is UnknownHostException -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }

        is IOException -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }

        else -> {
            com.example.domain.models.ApiResult.Error(
                com.example.domain.models.ErrorResponse(
                    message = message
                )
            )
        }
    }

    inline fun <reified T> convertToFlow(
        crossinline call: suspend () -> HttpResponse,
        crossinline success: suspend (data: T) -> Unit,
        crossinline failure: suspend () -> Unit
    ): Flow<com.example.domain.models.ApiResult<T>> =
        flow<com.example.domain.models.ApiResult<T>> {
            emit(com.example.domain.models.ApiResult.Loading(true))
            try {
                val response = call()
                emit(com.example.domain.models.ApiResult.Loading(false))
                success(response.body())
                emit(com.example.domain.models.ApiResult.Success(response.body()))
            } catch (e: Exception) {
                emit(com.example.domain.models.ApiResult.Loading(false))
                failure()
                emit(
                    com.example.domain.models.ApiResult.Error(
                        com.example.domain.models.ErrorResponse(
                            message = e.message
                        )
                    ))
            }
        }.flowOn(Dispatchers.IO)


    fun <T> Flow<T>.asResult(): Flow<Result<T>> {
        return this
            .map<T, Result<T>> {
                Result.Success(it)
            }
            .onStart { emit(Result.Loading) }
            .catch { emit(Result.Error(it)) }
    }

    @Composable
    fun LocalizeApp(language: String, content: @Composable () -> Unit) {
        val locale = Locale(language)
        val configuration = LocalConfiguration.current
        configuration.setLocale(locale)
        val resources = LocalContext.current.resources
        resources.updateConfiguration(configuration, resources.displayMetrics)

        CompositionLocalProvider(
            LocalLayoutDirection provides
                    if (LocalConfiguration.current.layoutDirection == LayoutDirection.Rtl.ordinal)
                        LayoutDirection.Rtl
                    else LayoutDirection.Ltr
        ) {

            content()

        }
    }

    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    @Composable
    fun fetchParamsFromDeeplink(): HashMap<String, String> {

        val params = hashMapOf<String, String>()

        val context = LocalContext.current
        val activity = context.findActivity()
        val queryParamsKeys = activity?.intent?.data?.queryParameterNames

        // GET ARGUMENTS FROM DEEPLINK
        queryParamsKeys?.forEach { queryParamKey ->
            params[queryParamKey] = activity.intent?.data?.getQueryParameter(queryParamKey) ?: ""
        }

        activity?.intent?.data = null
        return params
    }


    @Composable
    fun LocalizeApp(locale: Locale) {
        val configuration = LocalConfiguration.current
        configuration.setLocale(locale)
        val resources = LocalContext.current.resources
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }


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

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
    object Loading : Result<Nothing>
}