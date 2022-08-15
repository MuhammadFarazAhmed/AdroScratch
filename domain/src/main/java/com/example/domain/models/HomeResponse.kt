package com.example.domain.models

data class HomeResponse(
    val cmd: String,
    val code: Int,
    val `data`: Data,
    val http_response: Int,
    val message: String,
    val success: Boolean
)