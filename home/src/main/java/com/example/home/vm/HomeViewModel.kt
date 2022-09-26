package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.adro.base.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.domain.models.HomeResponse
import com.example.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    homeUseCase: HomeUseCase
) :
    AndroidViewModel(application) {

    init {
        fetchHomeData(homeUseCase)
    }

    private fun fetchHomeData(homeUseCase: HomeUseCase) {
        viewModelScope.launch {
            homeUseCase.fetchHome().handleErrors().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> sections.value = it.data?.data?.sections!!
                    ApiStatus.ERROR -> Log.d("TAG", "${it.message}: ")
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())

}