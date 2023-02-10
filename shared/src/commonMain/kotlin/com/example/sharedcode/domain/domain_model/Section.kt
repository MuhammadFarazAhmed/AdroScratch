package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("section_identifier")
    val sectionIdentifier: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("section_title")
    val sectionTitle: String? = null,
    @SerialName("sub_title")
    val subTitle: String? = null,
    @SerialName("button_title")
    val buttonTitle: String? = null,
    @SerialName("should_show_button")
    val shouldShowButton: Int? = null,
    @SerialName("button_bg_color")
    val buttonBgColor: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("deeplink")
    val deeplink: String? = null,
    @SerialName("login_popup")
    val loginPopup: LoginPopup? = null,
    @SerialName("section_items")
    val sectionItems: List<SectionItem> = arrayListOf()
)