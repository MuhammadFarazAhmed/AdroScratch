package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.OffersResponse
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(FlowPreview::class)
class SearchViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase
) :
    AndroidViewModel(application) {

    val isRefreshing = MutableStateFlow(false)
    val query = MutableStateFlow("")
    val offers: MutableStateFlow<PagingData<OffersResponse.Data.Outlet>> =
        MutableStateFlow(PagingData.empty())

    init {
        viewModelScope.launch {

            query.debounce(500L).collectLatest {
                if (it.isEmpty()) offers.value = PagingData.empty()
                else getOutlets()
            }

        }
    }

    private suspend fun getOutlets() {
        merchantUseCase.fetchOffers(
            isRefreshing,
            query = if (query.value != "") query.value else null,
            queryType = if (query.value != "") "name" else null,
        ).cachedIn(viewModelScope).collectLatest {
            offers.value = it
        }
    }

}