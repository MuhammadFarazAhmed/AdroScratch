package com.example.sharedcode.common

import com.example.sharedcode.security.CLibController
import io.ktor.client.*
import io.ktor.client.plugins.*

fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
    plugin(HttpSend).intercept { request ->
        when (request.attributes[CommonUtilsExtension.Apikey]) {
            CommonUtilsExtension.API.HOME -> request.url.host =
                cLibController.getENTBaseUrlOnline()
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