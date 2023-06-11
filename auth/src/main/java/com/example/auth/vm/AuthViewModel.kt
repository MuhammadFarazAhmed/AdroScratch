package com.example.auth.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.domain.models.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.domain.models.HomeResponse
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel constructor(
    application: Application,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(mobile, password).collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {}
                    ApiStatus.ERROR -> {
                        Log.d("TAG", "${it}: ")
                    }

                    ApiStatus.LOADING -> {}
                }
            }
        }
    }
}