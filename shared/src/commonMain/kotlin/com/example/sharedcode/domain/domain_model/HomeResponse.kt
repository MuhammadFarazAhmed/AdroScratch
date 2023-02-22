package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class HomeResponse(
    @SerialName("data")
    var `data`: Data? = null,
    @SerialName("success")
    var success: Boolean? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("cmd")
    var cmd: String? = null,
    @SerialName("http_response")
    var httpResponse: Int? = null,
    @SerialName("code")
    var code: Int? = null
) : CommonParcelable

fun HomeResponse.asDomainModel(): List<Home>? {
    return this.data?.sections?.map {
        Home(
            sectionIdentifier = it.sectionIdentifier,
            sectionItems = it.sectionItems as ArrayList<SectionItem>?,
            sortOrder = 1,
            title = it.sectionTitle,
            imageUrl = it.imageUrl,
            buttonTitle = it.buttonTitle,
            buttonBgColor = it.buttonBgColor,
            subTitle = it.subTitle
        )
    }
}

