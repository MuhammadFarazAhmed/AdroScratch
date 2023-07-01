package com.example.offers.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.models.ApiStatus
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import com.example.domain.usecase.MerchantUseCase
import com.example.adro.paging.BasePagingSource
import com.example.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class OffersViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)
    var params = hashMapOf<String, String>()
    val query = MutableStateFlow("")

    init {
        fetchTabs()
    }

    private fun fetchTabs() {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn().collectLatest { _ ->
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
    }

    fun refresh() {
        getOutlets()
    }

    private fun getOutlets() {
        viewModelScope.launch {
            authUseCase.isUserLoggedIn().collectLatest { _ ->
                Pager(PagingConfig(pageSize = 60)) {
                    BasePagingSource(MutableStateFlow(false)) {
                        merchantUseCase.fetchOffers(
                            params = selectedTab.value?.params,
                            query = if (query.value != "") query.value else null,
                            queryType = if (query.value != "") "name" else null
                        )
                    }
                }.flow.cachedIn(viewModelScope).collectLatest {
                    offers.value = it
                }
            }
        }
    }

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> = MutableStateFlow(emptyList())
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> = MutableStateFlow(
        PagingData.empty()
    )

    val selectedTab = MutableLiveData<TabsResponse.Data.Tab>()


}