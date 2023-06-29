package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.usecase.FavUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class FavoriteViewModel(application: Application, favUseCase: FavUseCase) :
    AndroidViewModel(application) {

    val favoriteList =
        Pager(PagingConfig(pageSize = 60)) { BasePagingSource(MutableStateFlow(false)) { favUseCase.fetchFavorites() } }.flow.cachedIn(
            viewModelScope
        )

}