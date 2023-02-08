package com.example.domain.usecase

import com.example.domain.models.FavoriteResponse

interface FavUseCase {

    suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet>

}