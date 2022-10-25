package com.example.sharedcode.presentation

import com.example.sharedcode.common.ApiStatus
import com.example.sharedcode.common.CommonFlowExtensions.handleErrors
import com.example.sharedcode.domain.domain_model.HomeResponse
import com.example.sharedcode.domain.usecase.HomeUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel constructor(
    homeUseCase: HomeUseCase
) :
    ViewModel() {

    init {
        fetchHomeData(homeUseCase)
    }

    private fun fetchHomeData(homeUseCase: HomeUseCase) {
        viewModelScope.launch {
            homeUseCase.fetchHome().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> sections.value = it.data?.data?.sections!!
                    ApiStatus.ERROR ->{} //Log.d("TAG", "${it.message}: ")
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())

}