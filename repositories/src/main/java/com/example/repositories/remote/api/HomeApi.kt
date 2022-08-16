package com.example.repositories.remote.api

import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface HomeApi {
    @POST("/ets_api/v5/home")
    suspend fun home(
        @Body body: HashMap<String, String> = HashMap<String, String>().apply {
            put("__company", "ADO")
            put("__lng", "0")
            put("device_key", "26525de9bd832a74")
            put("app_version", "1.0")
            put("lng", "0")
            put("device_model", "samsung%20SM-F916B")
            put("device_os_ver", "12")
            put("language", "en")
            put("build_no", "37")
            put("time_zone", "Asia/Karachi")
            put("device_os", "android")
            put("location_id", "2")
            put("device_uuid", "26525de9bd832a74")
            put("__platform", "android")
            put("device_uid", "26525de9bd832a74")
            put("__lat", "0")
            put("company", "ADO")
            put("currency", "AED")
            put("wlcompany", "ADO")
            put("lat", "0")
        }
    ): Response<HomeResponse>
}