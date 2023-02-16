package com.example.sharedcode.home.presentation

import com.example.sharedcode.common.Result
import com.example.sharedcode.common.asResult
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.sharedcode.domain.domain_model.Home
import com.example.sharedcode.domain.usecase.HomeUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Idle)
    var state = _state.asStateFlow()


    fun onIntent(intent: HomeScreenSideEvent) {

        when (intent) {
            is HomeScreenSideEvent.getHome -> {
                fetchHomeData()
            }
        }
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            homeUseCase.fetchHome().asResult().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _state.update {
                            HomeScreenState.Success(result.data)
                        }
                    }

                    is Result.Error -> {
                        _state.update {
                            HomeScreenState.Error(result.exception.message)
                        }
                    }

                    is Result.Idle -> {
                        _state.update {
                            HomeScreenState.Idle
                        }
                    }

                    is Result.Loading -> {
                        _state.update {
                            HomeScreenState.Loading
                        }
                    }
                }
            }
        }
    }

    val sections: MutableStateFlow<List<Home>> = MutableStateFlow(emptyList())

}