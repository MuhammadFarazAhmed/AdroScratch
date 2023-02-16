package com.example.sharedcode.home.presentation

import com.example.sharedcode.domain.domain_model.Home


sealed interface HomeScreenState{

    object Loading: HomeScreenState

    object Idle : HomeScreenState

    data class Success(val headlines: List<Home>) : HomeScreenState

    data class Error(val errorMessage: String) : HomeScreenState



}

