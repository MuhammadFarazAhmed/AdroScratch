package com.example.sharedcode.profile.data.repo



import com.example.sharedcode.domain.domain_model.ProfileResponse

interface ProfileRepository {

    suspend fun fetchProfile(): ProfileResponse

}