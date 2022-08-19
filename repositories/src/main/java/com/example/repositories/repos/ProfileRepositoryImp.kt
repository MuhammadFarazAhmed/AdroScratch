package com.example.repositories.repos

import com.example.domain.models.ProfileResponse
import com.example.domain.repos.ProfileRepository
import com.example.repositories.remote.api.ProfileApi

class ProfileRepositoryImp(private val profileApi: ProfileApi) : ProfileRepository {

    override suspend fun fetchProfile(): List<ProfileResponse.Data> =  profileApi.profile().body()!!.data

}
