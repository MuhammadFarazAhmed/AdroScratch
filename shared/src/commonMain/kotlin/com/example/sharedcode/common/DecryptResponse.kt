package com.example.sharedcode.common


import ApisEncryptionUtils
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.utils.io.*

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

            val decryptString = apisEncryptionUtils.decryptString(original.toByteArray().decodeToString())
                .also { /*Log.d("decryptedResponse", it)*/ }
            val decryptResponse = ByteReadChannel(decryptString)

            proceedWith(HttpResponseContainer(type, decryptResponse))
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