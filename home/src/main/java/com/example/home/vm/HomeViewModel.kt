package com.example.home.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.models.HomeResponse
import com.example.adro.paging.BasePagingSource
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    homeUseCase: HomeUseCase,
    authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)

    val sections: MutableStateFlow<PagingData<HomeResponse.Data.Section>> = MutableStateFlow(
        PagingData.empty()
    )

    init {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn().collectLatest { _ ->
                Pager(PagingConfig(pageSize = 60)) { BasePagingSource(isRefreshing) { homeUseCase.fetchHome() } }.flow.cachedIn(
                    viewModelScope
                ).collect {
                    sections.value = it
                }
            }
        }
    }


}