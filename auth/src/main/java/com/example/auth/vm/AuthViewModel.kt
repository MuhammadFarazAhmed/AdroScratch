package com.example.auth.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adro.models.ApiStatus
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

        val isLogin = authUseCase.isUserLoggedIn().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly, false)

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(mobile, password).collectLatest { res ->
                when (res.status) {
                    ApiStatus.SUCCESS -> {}
                    ApiStatus.ERROR -> {}
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }
}