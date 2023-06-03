package com.example.profile.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.models.ProfileResponse
import com.example.domain.models.TabsResponse
import com.example.domain.usecase.ProfileUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val profileUseCase: ProfileUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)
    val sections: MutableStateFlow<PagingData<ProfileResponse.Data>> =
        MutableStateFlow(PagingData.empty())

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
        Pager(PagingConfig(pageSize = 60)) { BasePagingSource(isRefreshing) { profileUseCase.fetchProfile() } }.flow.cachedIn(
            viewModelScope
        ).collect {
            sections.value = it
        }
    }


}