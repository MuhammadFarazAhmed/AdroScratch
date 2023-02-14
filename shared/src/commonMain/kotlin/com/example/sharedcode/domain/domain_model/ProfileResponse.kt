package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class ProfileResponse(
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("http_response")
    val httpResponse: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("success")
    val success: Boolean? = null
): CommonParcelable {
    @CommonParcelize
    @Serializable
    data class Data(
        @SerialName("section_data")
        val sectionData: List<SectionData>,
        @SerialName("section_identifier")
        val sectionIdentifier: String = "",
        @SerialName("section_title")
        val sectionTitle: String? = null
    ) : CommonParcelable {
        @Serializable
        @CommonParcelize
        data class SectionData(
            @SerialName("appVersion")
            val appVersion: String? = null,
            @SerialName("createAcc")
            val createAcc: String? = null,
            @SerialName("desc")
            val desc: String? = null,
            @SerialName("image")
            val image: String? = null,
            @SerialName("key")
            val key: String? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("signInBtn")
            val signInBtn: String? = null,
            @SerialName("title")
            val title: String? = null,
            @SerialName("type")
            val type: String? = null,
            @SerialName("value")
            val value: Int? = null
        ):CommonParcelable
    }
}

fun ProfileResponse.asList() = data