package com.example.adro.common

import android.util.Log
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.adro.*
import com.example.adro.base.ApiResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.Request
import retrofit2.Invocation
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object CommonUtilsExtension {
    
    //convert a map to a data class
    inline fun <reified T> Map<String, Any>.toDataClass(): T {
        return convert()
    }
    
    //convert an object of type I to type O
    inline fun <I, reified O> I.convert(): O {
        val gson = Gson()
        val json = gson.toJson(this)
        return gson.fromJson(json, object : TypeToken<O>() {}.type)
    }
    
    fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? {
        return this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
    }
    
     fun <T : Any> LazyListScope.applyPagination(lazyLayout: LazyPagingItems<T>) {
        lazyLayout.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyLayout.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(message = e.error.message,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { })
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyLayout.loadState.append as LoadState.Error
                    item {
                        ErrorItem(message = e.error.message, onClickRetry = { })
                    }
                }
            }
        }
    }
}