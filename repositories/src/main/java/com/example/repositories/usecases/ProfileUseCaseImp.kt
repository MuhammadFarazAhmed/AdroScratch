package com.example.repositories.usecases

import com.example.domain.models.ProfileResponse
import com.example.domain.repos.ProfileRepository
import com.example.domain.usecase.ProfileUseCase
import javax.inject.Inject

class ProfileUseCaseImp @Inject constructor(private val profileRepository: ProfileRepository) :
    ProfileUseCase {

    override suspend fun fetchProfile(): List<ProfileResponse.Data> = profileRepository.fetchProfile()
}

