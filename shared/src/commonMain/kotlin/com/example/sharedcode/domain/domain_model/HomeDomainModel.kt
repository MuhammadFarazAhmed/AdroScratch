package com.example.sharedcode.domain.domain_model

import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName


@CommonParcelize
data class Home(

    @SerialName("section_identifier")
    val sectionIdentifier: String? = null,
    @SerialName("section_title")
    val sectionTitle: String? = null,
    val sortOrder: Int? = 1,
    val title: String? = "",
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("deeplink")
    val deeplink: String? = null,
    @SerialName("login_popup")
    val loginPopup:  LoginPopup? = null,
    @SerialName("section_items")
    val sectionItems: List<SectionItem> = arrayListOf(),
    val buttonTitle: String? = null,
    @SerialName("should_show_button")
    val shouldShowButton: Int? = null,
    @SerialName("button_bg_color")
    val buttonBgColor: String? = null,
    @SerialName("sub_title")
    val subTitle: String? = null

    ) : CommonParcelable

