package com.example.home.vm

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.*
import com.example.domain.models.ApiStatus
import com.example.adro.common.CommonFlowExtensions.handleErrors
import com.example.domain.models.ConfigModel
import com.example.domain.models.HomeResponse
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val application: Application,
    private val homeUseCase: HomeUseCase,
    private val configDataStore: DataStore<ConfigModel>
) :
    AndroidViewModel(application) {

    init {
        fetchHomeData()
    }

    private fun fetchHomeData() {

        viewModelScope.launch {

            homeUseCase.fetchHome().handleErrors().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
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