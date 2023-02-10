package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class SectionItem(
    @SerialName("is_external_link")
    val isExternalLink: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("deeplink")
    val deeplink: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("subtitle")
    val subtitle: String? = null
):CommonParcelable