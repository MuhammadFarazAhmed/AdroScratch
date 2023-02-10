package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null,
    @SerialName("code")
    val code: Int? = null
)

fun HomeResponse.asDomainModel(): List<Home>? {
    return this.data?.sections?.map {
        Home(
            sectionIdentifier = it.sectionIdentifier,
            sectionItems = it.sectionItems,
            sortOrder = 0,
            title = it.sectionTitle,
            imageUrl = it.imageUrl,
            buttonTitle = it.buttonTitle,
            buttonBgColor = it.buttonBgColor,
            subTitle = it.subTitle
        )
    }
}