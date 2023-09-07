package com.example.adro.security

import android.util.Base64
import androidx.datastore.core.DataStore
import com.example.domain.models.LoginResponse
import com.theentertainerme.adro.security.CLibController
import com.theentertainerme.adro.security.XOREncryption
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.ktor.client.plugins.auth.AuthProvider
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.auth.HttpAuthHeader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BearerTokenProvider(private val userDataStore: DataStore<LoginResponse.Data.User>) : AuthProvider {

    private var token: String = ""

    init {
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            userDataStore.data.collectLatest {
                token = it.sessionToken ?: ""
            }
        }
    }

    private fun provideJWT(): String {
        return Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", "ADO")
            .claim("session_token", token)
            .claim("api_token", XOREncryption.decryptFromCKey(CLibController.getJApiToken()))
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(
                    XOREncryption.decryptFromCKey(CLibController.getSRKey()).toByteArray(),
                    Base64.DEFAULT
                )
            ).compact()
    }

    override val sendWithoutRequest: Boolean
        get() = true

    override suspend fun addRequestHeaders(
        request: HttpRequestBuilder,
        authHeader: HttpAuthHeader?
    ) {
        request.header("Authorization", "Bearer ${provideJWT()}")
    }

    override fun isApplicable(auth: HttpAuthHeader): Boolean {
      return true
    }
}