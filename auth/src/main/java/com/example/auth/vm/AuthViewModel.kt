package com.example.auth.vm

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.ApiStatus
import com.example.domain.models.LoginResponse
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application,
    private val authUseCase: AuthUseCase,
    private val userDataStore: DataStore<LoginResponse.Data.User>,
) :
    AndroidViewModel(application) {

    val loginSuccess = MutableStateFlow(false)

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(mobile, password).collect { res ->
                when (res.status) {
                    ApiStatus.SUCCESS -> {
                        loginSuccess.value = true
                    }

                    ApiStatus.ERROR -> {}
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }
}