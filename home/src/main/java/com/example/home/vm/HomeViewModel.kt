package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.*
import com.example.domain.models.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.adro.prefs.PreferenceDataStoreConstants
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.models.ConfigModel
import com.example.domain.models.HomeResponse
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    private val homeUseCase: HomeUseCase,
    private val preferencesHelper: PreferencesHelper
) :
    AndroidViewModel(application) {

    val isUserLoggedIn = MutableStateFlow(false)

    init {
        fetchHomeData()

        viewModelScope.launch {
            preferencesHelper.getPreference(PreferenceDataStoreConstants.IS_LOGGED_IN_KEY, false)
                .collectLatest {
                    isUserLoggedIn.value = it
                }
        }
    }

    fun fetchHomeData() {

        viewModelScope.launch {

            homeUseCase.fetchHome().handleErrors().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        it.data?.data?.sections?.let { sections.value = it }
                    }

                    ApiStatus.ERROR -> Log.d("TAG", "${it.message}: ")
                    ApiStatus.LOADING -> {}
                }
            }

        }
    }

    val sections: MutableStateFlow<List<HomeResponse.Data.Section>> = MutableStateFlow(emptyList())

}