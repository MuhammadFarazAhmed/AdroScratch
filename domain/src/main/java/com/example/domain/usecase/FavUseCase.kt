package com.example.domain.usecase

import com.example.adro.models.FavoriteResponse

interface FavUseCase {

    suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet>

}