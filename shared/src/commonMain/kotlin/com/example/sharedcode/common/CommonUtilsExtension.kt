package com.example.sharedcode.common

import io.ktor.client.request.*
import io.ktor.util.*

object CommonUtilsExtension {

//    //convert a map to a data class
//    inline fun <reified T> Map<String, Any>.toDataClass(): T {
//        return convert()
//    }
//
//    //convert an object of type I to type O
//    inline fun <I, reified O> I.convert(): O {
//        val gson = Gson()
//        val json = gson.toJson(this)
//        return gson.fromJson(json, object : TypeToken<O>() {}.type)
//    }
//
//    fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? {
//        return this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
//    }
//
    enum class API {
        HOME, PROFILE, FAV, OFFER
    }

    val Apikey = AttributeKey<API>("api")

    fun HttpRequestBuilder.setDefaultParams(api: API) {
        attributes.put(Apikey, api)
        setBody(
            mapOf(
                "__company" to "ADO",
                "__lng" to "0",
                "device_key" to "26525de9bd832a74",
                "app_version" to "1.0",
                "lng" to "0",
                "device_model" to "samsung%20SM-F916B",
                "device_os_ver" to "12",
                "language" to "en",
                "build_no" to "37",
                "time_zone" to "Asia/Karachi",
                "device_os" to "android",
                "location_id" to "2",
                "device_uuid" to "26525de9bd832a74",
                "__platform" to "android",
                "device_uid" to "26525de9bd832a74",
                "__lat" to "0",
                "company" to "ADO",
                "currency" to "AED",
                "wlcompany" to "ADO",
                "lat" to "0"
            )
        )
    }
}