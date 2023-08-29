package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ApiStatus
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommonViewModel(
    application: Application,
    private val commonUseCase: CommonUseCase
) :
    AndroidViewModel(application) {

    var keepOnSplashScreenOn = MutableStateFlow(true)

    val makeStatusBarTranslucent = MutableStateFlow(true)

    val language =
        commonUseCase.getLanguage().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "en")

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