package com.example.offers.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.adro.base.ApiStatus
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.usecase.OffersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    application: Application,
    private val offersUseCase: OffersUseCase
) :
    AndroidViewModel(application) {

    init {
        fetchTabs()
    }

    private fun fetchTabs() {
        viewModelScope.launch {
            offersUseCase.fetchTabs().collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> tabs.value = it.data?.data?.tabs
                    ApiStatus.ERROR -> {
                        Log.d("TAG", "${it.message}: ")
                    }
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    fun fetchOffers(tab: TabsResponse.Data.Tab) {
        viewModelScope.launch {
            offersUseCase.fetchOffers(tab.params).collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> offers.value = it.data?.data?.outlets
                    ApiStatus.ERROR -> {
                        Log.d("TAG", "${it.message}: ")
                    }
                    ApiStatus.LOADING -> {}
                }
            }
        }
    }

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> = MutableStateFlow(emptyList())

    val offers: MutableStateFlow<List<OffersResponse.Data.Outlet>?> = MutableStateFlow(emptyList())

}