package com.example.sharedcode.security


import com.example.sharedcode.CryptoService
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*

class DecryptResponse private constructor(
    private val cipher: CryptoService
) {

    class Config {
        lateinit var cipher: CryptoService
    }

    private fun decryptedResponse(client: HttpClient) {
        client.responsePipeline.intercept(HttpResponsePipeline.Receive) { (type, content) ->
            if (content !is ByteReadChannel) return@intercept

            val byteArray = ByteArray(content.availableForRead)
            content.readAvailable(byteArray)
            // Here we have original content untouched
            val original = ByteReadChannel(byteArray)

            val decryptString = cipher.decrypt(
                original.toByteArray(), "18b8c9ef473e2126c3c56ab0cb2b71cb".toByteArray(
                    Charset.forName("UTF-8")
                ), "18b8c9ef473e2126".toByteArray(
                    Charset.forName("UTF-8")
                )
            )
            Napier.d { decryptString }
            val decryptResponse = ByteReadChannel(decryptString, Charset.forName("UTF-8"))

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
            return DecryptResponse(config.cipher)
        }
    }
}

fun HttpClientConfig<*>.decryptResponse(block: DecryptResponse.Config.() -> Unit = {}) {
    install(DecryptResponse, block)
}