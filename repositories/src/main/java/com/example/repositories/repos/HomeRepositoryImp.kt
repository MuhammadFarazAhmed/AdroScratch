package com.example.repositories.repos

import com.example.domain.repos.HomeRepository
import com.example.repositories.remote.api.HomeApi
import javax.inject.Inject

class HomeRepositoryImp(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun fetchHome() {
        homeApi.home()
    }
}