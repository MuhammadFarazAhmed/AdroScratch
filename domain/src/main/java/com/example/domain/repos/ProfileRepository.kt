package com.example.domain.repos


import com.example.domain.models.ProfileResponse

interface ProfileRepository {

    suspend fun fetchProfile(): List<ProfileResponse.Data>

}