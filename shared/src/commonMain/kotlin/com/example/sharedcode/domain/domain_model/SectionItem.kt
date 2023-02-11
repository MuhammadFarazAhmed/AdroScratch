package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class SectionItem(
//    @SerialName("is_external_link")
//    var isExternalLink: Any = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("image_url")
    var imageUrl: String? = null,
    @SerialName("deeplink")
    var deeplink: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("subtitle")
    var subtitle: String? = null
) : CommonParcelable