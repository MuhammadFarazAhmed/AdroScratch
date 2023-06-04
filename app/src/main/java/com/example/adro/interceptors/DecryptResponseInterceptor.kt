package com.example.adro.interceptors

import android.util.Log
import com.example.adro.security.ApisEncryptionUtils
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.utils.io.*

class DecryptResponseInterceptor private constructor(private val apisEncryptionUtils: ApisEncryptionUtils) {

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

    companion object : HttpClientPlugin<Config, DecryptResponseInterceptor> {
        override val key: AttributeKey<DecryptResponseInterceptor>
            get() = AttributeKey("DecryptedResponse")

        override fun install(plugin: DecryptResponseInterceptor, scope: HttpClient) {
            plugin.decryptedResponse(scope)
        }

        override fun prepare(block: Config.() -> Unit): DecryptResponseInterceptor {
            val config = Config().apply(block)
            return DecryptResponseInterceptor(config.apisEncryptionUtils)
        }
    }
}

fun HttpClientConfig<*>.decryptResponse(block: DecryptResponseInterceptor.Config.() -> Unit = {}) {
    install(DecryptResponseInterceptor, block)
}