package com.example.sharedcode.offers.presentation

import com.example.sharedcode.common.Result
import com.example.sharedcode.common.asResult
import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.domain.usecase.MerchantUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.http.*
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class OffersSharedViewModel constructor(
    private val merchantUseCase: MerchantUseCase
) :
    ViewModel() {

    fun fetchTabs() {
        viewModelScope.launch {
            merchantUseCase.fetchTabs().asResult().collect {
                when (it) {
                    is Result.Success -> {
                        tabs.value = it.data.data?.tabs
                        fetchOffers()
                    }
                    is Result.Error -> Log.d("TAG", "fetchHomeData: ${it.exception.message}")
                    is Result.Idle -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    private suspend fun fetchOffers() {
        merchantUseCase.fetchOffers(selectedTab.value.params).asResult()
            .collectLatest { offerResponse ->
                when (offerResponse) {
                    is Result.Success -> offers.value = offerResponse.data
                    is Result.Error -> {}
                    Result.Idle -> {}
                    Result.Loading -> {}
                }
            }
    }


    val offers = MutableStateFlow<List<OffersResponse.Data.Outlet>>(emptyList())

    val tabs: MutableStateFlow<List<TabsResponse.Data.Tab?>?> = MutableStateFlow(emptyList())

    val selectedTab = MutableStateFlow(TabsResponse.Data.Tab())


}