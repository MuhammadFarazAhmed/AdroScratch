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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

object CommonExtensions {

    @Inject private lateinit var gson:Gson

    fun <T> Flow<T>.handleErrors(): Flow<T> =
        catch { e -> Log.d("TAG", "handleErrors: ", e.fillInStackTrace()) }

    fun <T> toResultFlow(call: suspend () -> Response<T>): Flow<ApiResult<T>> {
        return flow<ApiResult<T>> {
            emit(ApiResult.Loading(true))

            try {
                val result = call()
                if (result.isSuccessful) {
                    result.body()?.let {
                        emit(ApiResult.Success(result.body()))
                    }
                } else {
                    result.errorBody()?.let {
                        val error = it.string()
                        it.close()
                        emit(ApiResult.Error(error))
                    }
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
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

    //convert a map to a data class
    inline fun <reified T> Map<String, Any>.toDataClass(): T {
        return convert()
    }

    //convert an object of type I to type O
    inline fun <I, reified O> I.convert(): O {
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
}