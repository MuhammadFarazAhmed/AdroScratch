package com.example.sharedcode.home.presentation

import com.example.sharedcode.common.Result
import com.example.sharedcode.common.asResult
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import android.util.Log
import com.example.sharedcode.domain.domain_model.Home
import com.example.sharedcode.domain.usecase.HomeUseCase


class HomeViewModel constructor(homeUseCase: HomeUseCase) : ViewModel() {

    init {
        fetchHomeData(homeUseCase)
    }

    private fun fetchHomeData(homeUseCase: HomeUseCase) {
        viewModelScope.launch {
            homeUseCase.fetchHome().asResult().collectLatest {
                when (it) {
                    is Result.Success -> sections.value = it.data
                    is Result.Error -> {
                        Log.d("TAG", "fetchHomeData: ${it.exception.message}")}
                    is Result.Idle -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    val sections: MutableStateFlow<List<Home>> = MutableStateFlow(emptyList())

}