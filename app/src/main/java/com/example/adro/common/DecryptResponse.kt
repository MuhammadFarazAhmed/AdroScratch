package com.example.adro.common

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.nio.*

class DecryptResponse private constructor(private val apisEncryptionUtils: ApisEncryptionUtils) {

    class Config {
        lateinit var apisEncryptionUtils: ApisEncryptionUtils
    }

    private fun decryptedResponse(client: HttpClient) {
        client.responsePipeline.intercept(HttpResponsePipeline.Receive) { (type, content) ->
            if (content !is ByteReadChannel) return@intercept

            val byteArray = ByteArray(content.availableForRead)
            content.readAvailable(byteArray)
            // Here we have original content untouched
            val original = ByteReadChannel(byteArray)

            val decryptString = apisEncryptionUtils.decryptString(String(original.toByteArray()))
                .also { Log.d("decryptedResponse", it) }
            val decryptResponse = ByteReadChannel(decryptString)

            val response = HttpResponseContainer(type, decryptResponse)
            proceedWith(response)
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