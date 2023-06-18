package com.example.auth.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ApiStatus
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(mobile, password).collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {}
                    ApiStatus.ERROR -> {}
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }
}