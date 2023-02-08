package com.example.sharedcode.common


import io.ktor.client.*
import io.ktor.client.plugins.*

fun HttpClient.changeBaseUrlInterceptor() {
    plugin(HttpSend).intercept { request ->
        when (request.attributes[CommonUtilsExtension.Apikey]) {
            CommonUtilsExtension.API.HOME -> request.url.host =
                "apiutb2betentsrvpy.theentertainerme.com"
            CommonUtilsExtension.API.PROFILE -> request.url.host =
                "cLibController.getAuthBaseUrlOnline()"
            CommonUtilsExtension.API.FAV -> request.url.host =
                "cLibController.getOutletBaseUrlOnline()"
            CommonUtilsExtension.API.OFFER -> request.url.host =
               " cLibController.getOutletBaseUrlOnline()"
        }

        execute(request)
    }
}