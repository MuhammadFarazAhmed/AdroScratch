package com.example.sharedcode

import ApisEncryptionUtils
import android.os.Parcelable
import android.util.Base64
import com.example.sharedcode.common.changeBaseUrlInterceptor
import com.example.sharedcode.common.decryptResponse
import com.example.sharedcode.security.CLibController
import io.jsonwebtoken.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.parcelize.Parcelize
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable

actual fun platformModule() = module {
    single {
        Android.create()
    }
    
    single { CLibController() }
    
    single { ApisEncryptionUtils(get()) }
    
    single<String> {
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE).claim("company", "ADO")
                .claim("session_token", "")
                .claim("api_token", "k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4\$x").signWith(
                        SignatureAlgorithm.HS256,
                        Base64.encodeToString("!EyFde4#\$%gYsRct54fy@#\$5".toByteArray(),
                                Base64.DEFAULT)).compact()
    }
    
    single { createJson() }
    
    single {
        createHttpClient(httpClientEngine = get(),
                cLibController = get(),
                token = get(),
                encryptionUtils = get(),
                json = get())
    }
}


fun createHttpClient(httpClientEngine: HttpClientEngine,
                     cLibController: CLibController,
                     token: String,
                     encryptionUtils: ApisEncryptionUtils,
                     json: Json) = HttpClient(httpClientEngine) {
    
    defaultRequest {
        url {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            
            protocol = URLProtocol.HTTPS
            host = "apiutb2betentsrvpy.theentertainerme.com"
        }
    }
    
    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(token, token)
            }
        }
    }
    
    install(Logging) { level = LogLevel.ALL }
    
    install(ContentNegotiation) {
        json(json)
    }
    
    decryptResponse {
        apisEncryptionUtils = encryptionUtils
    }
    
}.apply {
    changeBaseUrlInterceptor(cLibController)
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }