package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.models.FavoriteResponse
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.FavoritesRepository
import com.example.domain.repos.OffersRepository
import com.example.domain.usecase.FavUseCase
import com.example.domain.usecase.OffersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavUseCaseImp @Inject constructor(private val favRepository: FavoritesRepository) :
    FavUseCase {
    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> = favRepository.fetchFavorites()
}

