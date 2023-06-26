package com.theentertainerme.adro.security

import android.util.Base64
import android.util.Log
import androidx.datastore.core.DataStore
import com.example.adro.models.LoginResponse
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class JWTProvider(private val userDataStore: DataStore<LoginResponse.Data.User>) {

    private var sessionToken: String = ""

    init {
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            userDataStore.data.collectLatest {
                sessionToken = it.sessionToken ?: ""
            }
        }
    }


    fun provideJWT(): String {
        return Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", "ADO")
            .claim("session_token", sessionToken)
            .claim("api_token", XOREncryption.decryptFromCKey(CLibController.getJApiToken()))
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(
                    XOREncryption.decryptFromCKey(CLibController.getSRKey()).toByteArray(),
                    Base64.DEFAULT
                )
            ).compact()
    }
}