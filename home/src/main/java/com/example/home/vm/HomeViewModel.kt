package com.example.home.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.HomeUseCase
import com.example.home.ui.models.HomeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application, homeUseCase: HomeUseCase) :
    AndroidViewModel(application) {

    val homeResponse = flow<HomeResponse> {
        homeUseCase.fetchHome()
    }


}