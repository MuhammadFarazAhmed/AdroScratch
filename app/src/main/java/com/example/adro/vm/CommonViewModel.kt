package com.example.adro.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.adro.base.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CommonViewModel(application: Application, private val commonRepository: CommonRepository) :
    AndroidViewModel(application) {

    init {
        getConfig()
    }

    private val configResponse = MutableStateFlow(ConfigModel())
    var keepOnSplashScreenOn = MutableStateFlow(true)

    private fun getConfig() {
        viewModelScope.launch {
            commonRepository.fetchConfig().collect {
                when (it) {
                    is ApiResult.Error -> {}
                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        keepOnSplashScreenOn.emit(false)
                        configResponse.value = it.data!!
                    }
                }
            }
        }
    }

}