package com.example.repositories.repos

import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension.API.OUTLET
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.models.FavoriteResponse
import com.example.adro.models.asList
import com.example.domain.repos.FavoritesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path

class FavRepositoryImp(
    private val client: HttpClient
) : FavoritesRepository {
    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> =
        try {
            val response = client.post {
                setDefaultParams(OUTLET)
                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as FavoriteResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
}

