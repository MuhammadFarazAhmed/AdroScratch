package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adro.common.CommonFlowExtensions.asResult
import com.example.adro.models.ApiStatus
import com.example.adro.models.ApiStatus.*
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import com.example.adro.paging.BasePagingSource
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class OffersViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)
    var params = hashMapOf<String, String>()
    val query = MutableStateFlow("")

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab>> = MutableStateFlow(emptyList())
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> = MutableStateFlow(
        PagingData.empty()
    )

    val selectedTab = MutableStateFlow(TabsResponse.Data.Tab())

    init {
        //Observer login and FetchTabs Api Response
        viewModelScope.launch {
            combine(
                authUseCase.isUserLoggedIn(),
                merchantUseCase.fetchTabs(params.apply {
                    put("is_adro_listing", "1")
                    put("is_offer", "true")
                }),
                ::Pair
            ).collectLatest {
                when (it.second.status) {
                    SUCCESS -> {
                        tabs.value = it.second.data?.data?.tabs ?: emptyList()
                        selectedTab.value =
                            it.second.data?.data?.tabs?.get(0) ?: TabsResponse.Data.Tab()
                    }

                    ERROR -> {}
                    LOADING -> {}
                }
            }
        }

        viewModelScope.launch {

            combine(
                selectedTab,
                query.debounce(500L),
                ::Pair
            )
                .collectLatest { pair -> getOutlets(pair.first) }

        }
    }

    private suspend fun getOutlets(tab: TabsResponse.Data.Tab) {
        Pager(PagingConfig(pageSize = 60)) {
            BasePagingSource(MutableStateFlow(false)) {
                merchantUseCase.fetchOffers(
                    query = if (query.value != "") query.value else null,
                    queryType = if (query.value != "") "name" else null,
                    tabsParams = tab.params,
                    params = hashMapOf("is_adro_listing" to "1", "is_offer" to "true")
                )
            }
        }.flow.cachedIn(viewModelScope).collectLatest {
            offers.value = it
        }
    }

}