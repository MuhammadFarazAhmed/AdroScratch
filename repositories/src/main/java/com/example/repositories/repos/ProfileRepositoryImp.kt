package com.example.repositories.repos

import com.example.domain.models.ProfileResponse
import com.example.domain.repos.ProfileRepository
import com.example.repositories.remote.api.ProfileApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileRepositoryImp(private val client: HttpClient) : ProfileRepository {

    override suspend fun fetchProfile(): List<ProfileResponse.Data> {
        val response = client.post {
            attributes.put(MyAttributeKey, "profileApi")
            url {
                path("et_user/v5/user/profile")
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
        return response.body()
    }

}
