package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.MerchantDetailModel
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MerchantDetailViewModel(
    application: Application,
    private val merchantUseCase: MerchantUseCase
) :
    AndroidViewModel(application) {


    val merchantDetailResponse =
        MutableStateFlow<List<MerchantDetailModel.Data.Detail>>(emptyList())

    init {

//        val params = hashMapOf("outletid" , "")

        viewModelScope.launch {
            merchantUseCase.fetchMerchantDetail("55957", hashMapOf()).collectLatest {
                it.data?.data?.details?.let {
                    merchantDetailResponse.value = it
                }
            }
        }
    }

}