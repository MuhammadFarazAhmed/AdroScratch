package com.example.adro.common

import android.util.Log
import androidx.compose.runtime.isTraceInProgress
import com.example.adro.security.ApisEncryptionUtils
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*

class DecryptResponse private constructor(private val apisEncryptionUtils: ApisEncryptionUtils) {
    
    class Config {
        lateinit var apisEncryptionUtils: ApisEncryptionUtils
    }
    
    private fun decryptedResponse(client: HttpClient) {
        client.receivePipeline.intercept(HttpReceivePipeline.After) { response ->
            Log.d("TAG",
                    "decryptedResponse: ${apisEncryptionUtils.decryptString(response.call.response.bodyAsText())}")
            //TODO set the response back to response body
            apisEncryptionUtils.decryptString(response.call.response.bodyAsText())
            proceedWith(subject)
        }
    }
    
    companion object : HttpClientPlugin<Config, DecryptResponse> {
        override val key: AttributeKey<DecryptResponse>
            get() = AttributeKey("DecryptedResponse")
        
        override fun install(plugin: DecryptResponse, scope: HttpClient) {
            plugin.decryptedResponse(scope)
        }
        
        override fun prepare(block: Config.() -> Unit): DecryptResponse {
            val config = Config().apply(block)
            return DecryptResponse(config.apisEncryptionUtils)
        }
    }
}

fun HttpClientConfig<*>.decryptResponse(block: DecryptResponse.Config.() -> Unit = {}) {
    install(DecryptResponse, block)
}