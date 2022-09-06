package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.toResultFlow
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

val MyAttributeKey = AttributeKey<String>("MyAttributeKey")

class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {


    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
        toResultFlow {
            client.post {
                attributes.put(MyAttributeKey, "homeApi")
                url {
                    path("ets_api/v5/home")
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
        }

}
