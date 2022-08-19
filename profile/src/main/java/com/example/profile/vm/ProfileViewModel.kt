package com.example.profile.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.usecase.ProfileUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val profileUseCase: ProfileUseCase
) :
    AndroidViewModel(application) {

    val sections =
        Pager(PagingConfig(pageSize = 60)) { BasePagingSource {  profileUseCase.fetchProfile() } }.flow.cachedIn(
            viewModelScope
        )


}