package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonExtensions.toResultFlow
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.ProfileRepository
import com.example.repositories.remote.api.HomeApi
import com.example.repositories.remote.api.ProfileApi
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImp(private val profileApi: ProfileApi) : ProfileRepository {

    override fun fetchProfile(): Flow<ApiResult<HomeResponse>> = toResultFlow { profileApi.profile() }

}
