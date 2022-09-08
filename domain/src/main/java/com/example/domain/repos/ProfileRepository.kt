package com.example.domain.repos


import com.example.adro.base.ApiResult
import com.example.domain.models.ProfileResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProfileRepository {

    suspend fun fetchProfile(): List<ProfileResponse.Data>

}