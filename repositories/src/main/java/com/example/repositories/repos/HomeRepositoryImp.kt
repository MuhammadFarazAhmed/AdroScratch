package com.example.repositories.repos

import androidx.compose.ui.graphics.vector.addPathNodes
import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.toResultFlow
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.example.repositories.remote.api.HomeApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {
    
    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
            toResultFlow { client.get { @com.example.repositories.annotations.HomeApi url { path("/ets_api/v5/home") } } }
    
}
