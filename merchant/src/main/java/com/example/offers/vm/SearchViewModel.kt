package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LoadStates
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.OffersResponse
import com.example.domain.usecase.MerchantUseCase
import com.example.adro.paging.BasePagingSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(FlowPreview::class)
class SearchViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase
) :
    AndroidViewModel(application) {

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
           query = if (query.value != "") query.value else null,
           queryType = if (query.value != "") "name" else null,
       ).cachedIn(viewModelScope).collectLatest {
            offers.value = it
        }
    }

}