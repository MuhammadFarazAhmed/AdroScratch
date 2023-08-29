package com.example.adro.interceptors

import com.example.adro.common.CommonUtilsExtension
import com.theentertainerme.adro.security.CLibController
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.host
import io.ktor.http.HttpMethod
import io.ktor.util.AttributeKey

class ChangeBaseUrlInterceptor private constructor() {

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

    companion object : HttpClientPlugin<Config, ChangeBaseUrlInterceptor> {
        override val key: AttributeKey<ChangeBaseUrlInterceptor>
            get() = AttributeKey("ChangeBaseUrl")

        override fun install(plugin: ChangeBaseUrlInterceptor, scope: HttpClient) {
            plugin.changeBaseUrl(scope)
        }

        override fun prepare(block: Config.() -> Unit): ChangeBaseUrlInterceptor {
            return ChangeBaseUrlInterceptor()
        }
    }

}

fun HttpClientConfig<*>.changeBaseUrlInterceptor(block: ChangeBaseUrlInterceptor.Config.() -> Unit = {}) {
    install(ChangeBaseUrlInterceptor, block)
}