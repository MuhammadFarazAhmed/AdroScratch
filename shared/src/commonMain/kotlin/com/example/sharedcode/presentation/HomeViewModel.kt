package com.example.sharedcode.presentation

import com.example.sharedcode.common.ApiStatus
import com.example.sharedcode.common.asResult
import com.example.sharedcode.domain.domain_model.HomeResponse
import com.example.sharedcode.domain.usecase.HomeUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.sharedcode.common.Result


class HomeViewModel constructor(homeUseCase: HomeUseCase) : ViewModel() {
    
    init {
        fetchHomeData(homeUseCase)
    }
    
    private fun fetchHomeData(homeUseCase: HomeUseCase) {
        viewModelScope.launch {
            homeUseCase.fetchHome().asResult().collectLatest {
                when (it) {
                    is Result.Error -> {}
                    Result.Idle -> {}
                    Result.Loading -> {}
                    is Result.Success -> sections.value = it.data.data?.sections!!
                }
//                        ApiStatus.SUCCESS -> sections.value = it.data?.data?.sections!!
//                        ApiStatus.ERROR ->{} //Log.d("TAG", "${it.message}: ")
//                        ApiStatus.LOADING -> {}
            }
        }
    }
    
    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())
    
}