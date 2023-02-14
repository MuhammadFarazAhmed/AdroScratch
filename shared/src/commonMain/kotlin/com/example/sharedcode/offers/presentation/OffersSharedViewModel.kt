package com.example.sharedcode.offers.presentation

import com.example.sharedcode.common.Result
import com.example.sharedcode.common.asResult
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.domain.usecase.MerchantUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class OffersSharedViewModel constructor(
    private val merchantUseCase: MerchantUseCase
) :
    ViewModel() {


    fun fetchTabs(callback: () -> Unit) {
        viewModelScope.launch {
            merchantUseCase.fetchTabs().asResult().collect {
                when (it) {
                    is Result.Success -> {
                        tabs.value = it.data.data?.tabs
                        callback()
                    }
                    is Result.Error -> Napier.d { it.exception.message }
                    is Result.Idle -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?) = merchantUseCase.fetchOffers(params)


    val offers = MutableStateFlow<List<OffersResponse.Data.Outlet>>(emptyList())

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> =
        MutableStateFlow(emptyList())

    val selectedTab = MutableStateFlow(TabsResponse.Data.Tab())


}