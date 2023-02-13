package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.ProfileResponse
import com.example.sharedcode.profile.data.repo.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileUseCaseImp constructor(private val profileRepository: ProfileRepository) :
    ProfileUseCase {

    override suspend fun fetchProfile(): Flow<ProfileResponse> = flow {  profileRepository.fetchProfile() }
}

