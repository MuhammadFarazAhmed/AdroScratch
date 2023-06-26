package com.example.adro.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null
)