package com.example.adro.common

import com.example.adro.common.CommonUtilsExtension.API.*
import com.theentertainerme.adro.security.CLibController
import io.ktor.client.*
import io.ktor.client.plugins.*

fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
    plugin(HttpSend).intercept { request ->
        when (request.attributes[CommonUtilsExtension.Apikey]) {
            CORE -> cLibController.getENTBaseUrlOnline()
            AUTH -> cLibController.getAuthBaseUrlOnline()
            MERCHANT -> cLibController.getMerchantBaseUrlOnline()
            PROFILE -> cLibController.getProfileBaseUrlOnline()
            OUTLET -> cLibController.getOutletBaseUrlOnline()
            REDEMPTION -> cLibController.getRedemptionBaseUrlOnline()
        }

        execute(request)
    }
}