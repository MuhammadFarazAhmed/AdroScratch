package com.example.domain.repos


import com.example.adro.models.FavoriteResponse

interface FavoritesRepository {

    suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet>

}