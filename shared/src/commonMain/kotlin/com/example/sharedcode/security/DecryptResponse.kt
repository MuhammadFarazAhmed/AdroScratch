package com.example.sharedcode.security


import com.example.sharedcode.getOriginalResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.utils.io.*

class DecryptResponse private constructor() {

    class Config {

    }

    private fun decryptedResponse(client: HttpClient) {
        client.responsePipeline.intercept(HttpResponsePipeline.Receive) { (type, content) ->
            if (content !is ByteReadChannel) return@intercept

            val byteArray = ByteArray(content.availableForRead)
            content.readAvailable(byteArray)
            // Here we have original content untouched
            val original = ByteReadChannel(byteArray)

            val decryptString = getOriginalResponse(original.toByteArray().encodeBase64())
                .also { android.util.Log.d("TAG", "decryptedResponse: $it") }
            val decryptResponse = ByteReadChannel(decryptString.toString())

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
//            val config = Config().apply(block)
            return DecryptResponse()
        }
    }
}

fun HttpClientConfig<*>.decryptResponse(block: DecryptResponse.Config.() -> Unit = {}) {
    install(DecryptResponse, block)
}