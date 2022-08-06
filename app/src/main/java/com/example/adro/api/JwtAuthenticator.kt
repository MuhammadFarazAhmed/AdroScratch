package com.example.adro.api

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class JwtAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {

        return response.request.newBuilder()
            //.header(ApiClient.HEADER_AUTHORIZATION, updatedToken)
            .build()
    }

    private fun getUpdatedToken(): String {
//        val requestParams = HashMap<String, String>()
//        val authTokenResponse = ApiClient.userApiService.getAuthenticationToken(requestParams).execute().body()!!
//
//        val newToken = "${authTokenResponse.tokenType} ${authTokenResponse.accessToken}"
        return "newToken"
    }
}