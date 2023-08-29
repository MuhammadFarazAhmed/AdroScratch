package com.example.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LogoutModel(
    @SerialName("message")
    val message: String?,
    @SerialName("success")
    val success: Boolean?,
    @SerialName("data")
    val `data`: List<String>?,
    @SerialName("code")
    val code: Int?,
    @SerialName("cmd")
    val cmd: String?,
    @SerialName("http_response")
    val httpResponse: Int?
)