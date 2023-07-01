package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adro.common.Result
import com.example.adro.models.ApiResult
import com.example.adro.models.ApiStatus
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommonViewModel(
    application: Application,
    private val commonUseCase: CommonUseCase
) :
    AndroidViewModel(application) {

    var keepOnSplashScreenOn = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            commonUseCase.fetchConfig().collectLatest { apiResult ->
                when (apiResult.status) {
                    ApiStatus.ERROR ->
                        Log.d("TAG", "getConfig: error ${apiResult.message}")

                    ApiStatus.LOADING -> {}
                    ApiStatus.SUCCESS -> keepOnSplashScreenOn.value = false
                }
            }
        }
    }
}