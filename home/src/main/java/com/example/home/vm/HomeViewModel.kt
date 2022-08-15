package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import com.example.adro.base.ApiStatus
import com.example.adro.common.CommonExtensions.handleErrors
import com.example.domain.models.Section
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
        viewModelScope.launch {
            homeUseCase.fetchHome().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> sections.value = it.data?.data?.sections!!
                    ApiStatus.ERROR -> {
                        Log.d("TAG", "${it.message}: ")
                    }
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    val sections: MutableStateFlow<List<Section>> = MutableStateFlow(emptyList())

}