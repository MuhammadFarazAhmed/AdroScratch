package com.example.sharedcode.profile.presentation

import com.example.sharedcode.common.Result
import com.example.sharedcode.common.asResult
import com.example.sharedcode.domain.domain_model.ProfileResponse
import com.example.sharedcode.domain.usecase.ProfileUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class ProfileViewModel constructor(
    private val profileUseCase: ProfileUseCase
) :
    ViewModel() {

    init {
        getProfileSections()
    }

    var sections = MutableStateFlow(listOf<ProfileResponse.Data>())
    private fun getProfileSections() {
        viewModelScope.launch {
            profileUseCase.fetchProfile().asResult().collectLatest {
                when (it) {
                    is Result.Error -> {}
                    Result.Idle -> {}
                    Result.Loading -> {}
                    is Result.Success ->  sections.value  =  it.data.data
                }
            }
        }
    }


}