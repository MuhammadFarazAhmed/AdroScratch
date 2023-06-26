package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.adro.models.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.adro.models.HomeResponse
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    private val homeUseCase: HomeUseCase,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn().collectLatest { _ -> refresh() }
        }
    }

    fun refresh() {
        fetchHomeData()
    }

    private fun fetchHomeData() {

        viewModelScope.launch {

            homeUseCase.fetchHome().handleErrors().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        isRefreshing.emit(false)
                        it.data?.data?.sections?.let { sections.value = it }
                    }

                    ApiStatus.ERROR -> {
                        isRefreshing.emit(false)
                        Log.d("TAG", "${it.message}: ")
                    }

                    ApiStatus.LOADING -> {
                        isRefreshing.emit(true)
                    }
                }
            }

        }
    }

    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())

}