package com.example.offers.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.ApiStatus
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.usecase.MerchantUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OffersViewModel @Inject constructor(
    application: Application,
    private val merchantUseCase: MerchantUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)
    var params = hashMapOf<String, String>()

    init {
        fetchTabs()
    }

    private fun fetchTabs() {
        viewModelScope.launch {
            merchantUseCase.fetchTabs(params).collect {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        isRefreshing.emit(false)
                        tabs.value = it.data?.data?.tabs
                        getOutlets()
                    }

                    ApiStatus.ERROR -> {
                        isRefreshing.emit(false)
                        Log.d("TAG", "${it.message}: ")
                    }

                    ApiStatus.LOADING -> isRefreshing.emit(true)
                }
            }
        }
    }

    fun refresh() {
        fetchTabs()
    }

    private fun getOutlets() {
        viewModelScope.launch {
            Pager(PagingConfig(pageSize = 60)) {
                BasePagingSource(MutableStateFlow(false)) {
                    merchantUseCase.fetchOffers(selectedTab.value!!.params)
                }
            }.flow.cachedIn(viewModelScope).collectLatest {
                offers.value = it
            }
        }
    }

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> = MutableStateFlow(emptyList())
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> = MutableStateFlow(
        PagingData.empty()
    )

    val selectedTab = MutableLiveData<TabsResponse.Data.Tab>()


}