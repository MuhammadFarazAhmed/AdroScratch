package com.example.adro.common

import android.util.Base64
import android.util.Log
import com.example.adro.security.ApisEncryptionUtils
import com.example.domain.models.HomeResponse
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import io.ktor.util.*
import io.ktor.util.Identity.decode
import io.ktor.utils.io.*
import io.ktor.utils.io.copyTo
import io.ktor.utils.io.jvm.nio.*
import okhttp3.ResponseBody.Companion.toResponseBody
import java.nio.ByteBuffer
import java.nio.charset.Charset

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

            val decryptResponse = ByteReadChannel(apisEncryptionUtils.decryptString(original.readUTF8Line()))

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