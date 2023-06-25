package com.example.adro.interceptors

import android.util.Log
import com.example.adro.security.ApisEncryptionUtils
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.AttributeKey
import io.ktor.util.Identity.decode
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readAvailable

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
                .also { it?.let { it1 -> Log.d("decryptedResponse", it1) } }

            if (decryptString == null) {
                proceedWith(HttpResponseContainer(type, ByteReadChannel(byteArray)))
            } else
                proceedWith(HttpResponseContainer(type, ByteReadChannel(decryptString)))
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