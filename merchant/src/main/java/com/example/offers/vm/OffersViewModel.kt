package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.ApiStatus.*
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(FlowPreview::class)
class OffersViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase,
    private val authUseCase: AuthUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)

    var deepLinkParams = MutableStateFlow<HashMap<String, String>>(hashMapOf())

    val query = MutableStateFlow("")
    val selectedTab = MutableStateFlow(TabsResponse.Data.Tab())

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab>> = MutableStateFlow(emptyList())
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> = MutableStateFlow(
        PagingData.empty()
    )


    init {

        //Wait for params to get tabs
        viewModelScope.launch {

            deepLinkParams.collectLatest { params ->
                getTabs(params)
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

    private suspend fun getTabs(params: HashMap<String, String>) {
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
                    isRefreshing.emit(false)
                }

                ERROR -> {
                    isRefreshing.emit(false)
                }

                LOADING -> {
                    isRefreshing.emit(true)
                }
            }
        }

    }

    private suspend fun getOutlets(tab: TabsResponse.Data.Tab) {
        merchantUseCase.fetchOffers(
            isRefreshing,
            tabsParams = tab.params,
            query = if (query.value != "") query.value else null,
            queryType = if (query.value != "") "name" else null,
            params = hashMapOf("is_adro_listing" to "1", "is_offer" to "true")
        ).cachedIn(viewModelScope).collectLatest {
            offers.value = it
        }
    }

}