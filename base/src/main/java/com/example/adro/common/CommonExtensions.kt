package com.example.adro.common

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

object CommonExtensions {
    
    fun <T> Flow<T>.handleErrors(): Flow<T> =
            catch { e -> Log.d("TAG", "handleErrors: ", e.fillInStackTrace()) }
    
    
}