package com.example.auth.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ApiStatus
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val loginSuccess = MutableStateFlow(false)

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(mobile, password).collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> loginSuccess.value = true
                    ApiStatus.ERROR -> {}
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }
}