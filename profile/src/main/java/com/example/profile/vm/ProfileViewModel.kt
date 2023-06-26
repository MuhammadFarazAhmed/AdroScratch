package com.example.profile.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.adro.models.ProfileResponse
import com.example.domain.usecase.AuthUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileViewModel(
    application: Application,
    private val authUseCase: AuthUseCase
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
        refresh()
    }


    fun refresh() {
        viewModelScope.launch {
            isRefreshing.emit(true)
            getProfile()
        }
    }

    private suspend fun getProfile() {
        Pager(PagingConfig(pageSize = 60)) { BasePagingSource(isRefreshing) { authUseCase.fetchProfile() } }.flow.cachedIn(
            viewModelScope
        ).collect {
            sections.value = it
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authUseCase.logOut().handleErrors().collectLatest { success ->
                Log.d("TAG", "signOut: $success")
            }
        }
    }


}