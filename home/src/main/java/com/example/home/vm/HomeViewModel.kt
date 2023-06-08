package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.*
import com.example.domain.models.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.adro.prefs.ConfigPreferencesHelper
import com.example.domain.models.ConfigModel
import com.example.domain.models.HomeResponse
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel constructor(
    private val application: Application,
    homeUseCase: HomeUseCase,
    private val configDataStore: DataStore<ConfigModel>,
    private val configPreferencesHelper: ConfigPreferencesHelper
) :
    AndroidViewModel(application) {

    init {
        fetchHomeData(homeUseCase)
        viewModelScope.launch {

        }
    }

    private fun fetchHomeData(homeUseCase: HomeUseCase) {

        viewModelScope.launch {
            homeUseCase.fetchHome().handleErrors().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        viewModelScope.launch {
                            configDataStore.data.collectLatest {
                                Log.d("TAG", "fetchHomeData: ${it.message}")
                            }
                        }
                        sections.value = it.data?.data?.sections!!
                    }

                    ApiStatus.ERROR -> Log.d("TAG", "${it.message}: ")
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())

}