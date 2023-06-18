package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommonViewModel(
    application: Application,
    private val commonUseCase: CommonUseCase,
) :
    AndroidViewModel(application) {

    init {
        getConfig()
    }

    var keepOnSplashScreenOn = MutableStateFlow(true)

    private fun getConfig() {
        viewModelScope.launch {
            commonUseCase.fetchConfig().collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Error ->
                        Log.d("TAG", "getConfig: error ${apiResult.exception}")
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        keepOnSplashScreenOn.value  = false
                    }
                }
            }
        }
    }

}