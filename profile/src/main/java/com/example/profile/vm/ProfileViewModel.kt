package com.example.profile.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.adro.base.ApiStatus
import com.example.adro.common.CommonExtensions.handleErrors
import com.example.domain.models.Section
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private val profileUseCase: ProfileUseCase
) :
    AndroidViewModel(application) {

    init {
        fetchHomeData()
    }

    fun fetchHomeData() {
        viewModelScope.launch {
            profileUseCase.fetchHome().handleErrors().collect {
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