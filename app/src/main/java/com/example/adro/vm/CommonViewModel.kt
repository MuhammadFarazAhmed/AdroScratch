package com.example.adro.vm

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adro.prefs.ConfigPreferencesHelper
import com.example.adro.prefs.ConfigPreferencesSerializer
import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CommonViewModel(
    private val application: Application,
    private val commonRepository: CommonRepository,
    private val configDataStore: DataStore<ConfigModel>,
    private val configPreferencesHelper: ConfigPreferencesHelper
) :
    AndroidViewModel(application) {

    init {
        getConfig()
    }

    private val configResponse = MutableStateFlow(ConfigModel())
    var keepOnSplashScreenOn = MutableStateFlow(true)

    private fun getConfig() {
        viewModelScope.launch {
            commonRepository.fetchConfig().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> {
                        Log.d("TAG", "getConfig: error ${apiResult.exception.first()}")
                    }

                    is ApiResult.Loading -> {}
                    is ApiResult.Success -> {
                        configResponse.value = apiResult.data!!
                        configDataStore.updateData { it.copy(message = "it is changed in config") }
                        keepOnSplashScreenOn.emit(false)
                    }
                }
            }
        }
    }

}