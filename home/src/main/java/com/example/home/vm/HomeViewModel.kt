package com.example.home.vm

import android.app.Application
import androidx.lifecycle.*
import com.example.adro.common.CommonExtensions.handleErrors
import com.example.domain.usecase.HomeUseCase
import com.example.home.ui.models.Section
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel class HomeViewModel @Inject constructor(application: Application,
                                                       homeUseCase: HomeUseCase) :
    AndroidViewModel(application) {
    
    val sections: Flow<List<Section>?> =
            homeUseCase.fetchHome().handleErrors().map { it.data?.data?.sections }
    
}