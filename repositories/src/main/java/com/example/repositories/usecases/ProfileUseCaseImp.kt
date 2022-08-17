package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.ProfileRepository
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.ProfileUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ProfileUseCaseImp @Inject constructor(private val profileRepository: ProfileRepository) :
    ProfileUseCase {

    override fun fetchHome() = profileRepository.fetchHome()
}

