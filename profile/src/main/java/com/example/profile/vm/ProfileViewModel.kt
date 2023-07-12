package com.example.profile.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.adro.models.LoginResponse
import com.example.adro.models.ProfileResponse
import com.example.domain.usecase.AuthUseCase
import com.example.adro.paging.BasePagingSource
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    application: Application,
    private val authUseCase: AuthUseCase,
    private val commonUseCase: CommonUseCase,
) :
    AndroidViewModel(application) {


    val isRefreshing = MutableStateFlow(false)
    val sections: MutableStateFlow<PagingData<ProfileResponse.Data>> =
        MutableStateFlow(PagingData.empty())

    val isLogin = authUseCase.isUserLoggedIn().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )


    init {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn().collectLatest {
                isRefreshing.emit(true)
                Pager(PagingConfig(pageSize = 60)) { BasePagingSource(isRefreshing) { authUseCase.fetchProfile() } }.flow.cachedIn(
                    viewModelScope
                ).collect {
                    sections.value = it
                }
            }
        }
    }

    fun setLanguage(language: String) {
        commonUseCase.setLanguage(language)
    }

    val user = authUseCase.getUser()
        .stateIn(viewModelScope, SharingStarted.Eagerly, LoginResponse.Data.User())

    val selectedLanguage =
        commonUseCase.getLanguage().stateIn(viewModelScope, SharingStarted.Eagerly, "")


    fun signOut() {
        viewModelScope.launch {
            authUseCase.logOut().handleErrors().collectLatest { success ->
                Log.d("TAG", "signOut: $success")
            }
        }
    }


}