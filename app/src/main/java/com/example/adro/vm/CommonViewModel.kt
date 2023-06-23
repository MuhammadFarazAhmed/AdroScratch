package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.models.ApiResult
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommonViewModel(
    application: Application,
    private val commonUseCase: CommonUseCase,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    var isLogin = MutableStateFlow(false)
    var keepOnSplashScreenOn = MutableStateFlow(true)

    init {
        getConfig()
        observerLogin()
    }

    private fun observerLogin() {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn()
                .collectLatest {
                    isLogin.value = it
                }
        }
    }

    private fun getConfig() {
        viewModelScope.launch {
            commonUseCase.fetchConfig().collectLatest { apiResult ->
                when (apiResult) {
                    is ApiResult.Error ->
                        Log.d("TAG", "getConfig: error ${apiResult.exception}")

                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> keepOnSplashScreenOn.value = false
                }
            }
        }
    }

}