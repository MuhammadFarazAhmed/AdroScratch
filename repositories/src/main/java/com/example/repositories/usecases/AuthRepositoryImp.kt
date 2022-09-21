package com.example.repositories.usecases

import com.example.domain.repos.AuthRepository
import io.ktor.client.*
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val client: HttpClient) :
    AuthRepository {

}

