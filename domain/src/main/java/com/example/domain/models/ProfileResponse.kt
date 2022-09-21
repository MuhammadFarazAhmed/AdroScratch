package com.example.domain.models


import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("cmd")
    val cmd: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("http_response")
    val httpResponse: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
) {
    data class Data(
        @SerializedName("section_data")
        val sectionData: List<SectionData>,
        @SerializedName("section_identifier")
        val sectionIdentifier: String = "",
        @SerializedName("section_title")
        val sectionTitle: String? = null
    ) {
        data class SectionData(
            @SerializedName("appVersion")
            val appVersion: String? = null,
            @SerializedName("createAcc")
            val createAcc: String? = null,
            @SerializedName("desc")
            val desc: String? = null,
            @SerializedName("image")
            val image: String? = null,
            @SerializedName("key")
            val key: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("signInBtn")
            val signInBtn: String? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("type")
            val type: String? = null,
            @SerializedName("value")
            val value: Int? = null
        )
    }
}

fun ProfileResponse.asList() = data