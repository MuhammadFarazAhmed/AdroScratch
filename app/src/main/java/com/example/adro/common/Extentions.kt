package com.example.adro.common

import com.example.adro.common.CommonUtilsExtension.API.*
import com.theentertainerme.adro.security.CLibController
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.host

fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
    plugin(HttpSend).intercept { request ->
        when (request.attributes[CommonUtilsExtension.Apikey]) {
            CORE -> request.host = cLibController.getENTBaseUrlOnline()
            USER -> request.host = cLibController.getAuthBaseUrlOnline()
            CONFIG -> request.host = cLibController.getConfigBaseUrlOnline()
            MERCHANT -> request.host = cLibController.getMerchantBaseUrlOnline()
            ACCOUNT -> request.host = cLibController.getProfileBaseUrlOnline()
            OUTLET -> request.host = cLibController.getOutletBaseUrlOnline()
            REDEMPTION -> request.host = cLibController.getRedemptionBaseUrlOnline()
        }

        execute(request)
    }
}