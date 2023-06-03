package com.example.adro.common

import android.util.Log
import com.theentertainerme.adro.security.CLibController
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.host
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.utils.HttpRequestCreated
import io.ktor.http.HttpMethod
import io.ktor.util.AttributeKey
import io.ktor.utils.io.ByteReadChannel

class ChangeBaseUrl private constructor() {

    class Config

    private fun changeBaseUrl(client: HttpClient) {
        client.plugin(HttpSend).intercept { request ->
            val requestBuilder = HttpRequestBuilder()
            when (request.attributes[CommonUtilsExtension.Apikey]) {
                CommonUtilsExtension.API.CORE -> request.host = CLibController.getENTBaseUrlOnline()
                CommonUtilsExtension.API.USER -> request.host = CLibController.getAuthBaseUrlOnline()
                CommonUtilsExtension.API.CONFIG -> request.host = CLibController.getConfigBaseUrlOnline()
                CommonUtilsExtension.API.MERCHANT -> request.host = CLibController.getMerchantBaseUrlOnline()
                CommonUtilsExtension.API.ACCOUNT -> request.host = CLibController.getProfileBaseUrlOnline()
                CommonUtilsExtension.API.OUTLET -> request.host = CLibController.getOutletBaseUrlOnline()
                CommonUtilsExtension.API.REDEMPTION -> request.host = CLibController.getRedemptionBaseUrlOnline()
                else -> requestBuilder.host = CLibController.getENTBaseUrlOnline()
            }
            requestBuilder.method = HttpMethod.Post

            execute(request)
        }
    }

    companion object : HttpClientPlugin<Config, ChangeBaseUrl> {
        override val key: AttributeKey<ChangeBaseUrl>
            get() = AttributeKey("ChangeBaseUrl")

        override fun install(plugin: ChangeBaseUrl, scope: HttpClient) {
            plugin.changeBaseUrl(scope)
        }

        override fun prepare(block: Config.() -> Unit): ChangeBaseUrl {
            return ChangeBaseUrl()
        }
    }

}

fun HttpClientConfig<*>.changeBaseUrlInterceptor(block: ChangeBaseUrl.Config.() -> Unit = {}) {
    install(ChangeBaseUrl, block)
}