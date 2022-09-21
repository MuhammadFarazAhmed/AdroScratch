package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.repos.AuthRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AuthUseCaseImp @Inject constructor(private val authRepository: AuthRepository) : AuthUseCase {

}

