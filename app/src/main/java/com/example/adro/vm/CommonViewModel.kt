package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.models.ApiResult
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommonViewModel(
    application: Application,
    private val commonUseCase: CommonUseCase,
    private val preferencesHelper: PreferencesHelper
) :
    AndroidViewModel(application) {

    var isLogin = MutableStateFlow(false)

    init {
        getConfig()
        viewModelScope.launch {
            preferencesHelper.getPreference(booleanPreferencesKey("is_login"), false)
                .collectLatest {
                    isLogin.value = it
                }
        }
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
                        keepOnSplashScreenOn.value = false
                    }
                }
            }
        }
    }

}