package com.example.adro.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class JWTInterceptor @Inject constructor(private val jwtToken: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $jwtToken").build()
        return chain.proceed(request)
    }
}