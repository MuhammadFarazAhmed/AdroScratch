package com.example.home.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.sharedcode.domain.domain_model.Home
import com.example.sharedcode.domain.domain_model.HomeResponse
import com.example.sharedcode.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel constructor(
    application: Application,
    homeUseCase: HomeUseCase
) :
    AndroidViewModel(application) {

    init {
        //fetchHomeData(homeUseCase)
    }

//    private fun fetchHomeData(homeUseCase: HomeUseCase) {
//        viewModelScope.launch {
//            homeUseCase.fetchHome().handleErrors().collect {
////                when (it.status) {
////                    ApiStatus.SUCCESS -> sections.value = it.data?.data?.sections!!
////                    ApiStatus.ERROR -> Log.d("TAG", "${it.message}: ")
////                    ApiStatus.LOADING -> {}
////                }
//            }
//        }
//    }

    val sections: MutableStateFlow<List<Home>> = MutableStateFlow(emptyList())

}