package com.example.repositories.usecases

import com.example.domain.models.FavoriteResponse
import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.FavUseCase

class FavUseCaseImp(private val merchantRepository: MerchantRepository) :
    FavUseCase {

    override suspend fun fetchFavorites(params: HashMap<String, String>): List<FavoriteResponse.Data.Outlet> =
        merchantRepository.fetchFavorites(params)

}

