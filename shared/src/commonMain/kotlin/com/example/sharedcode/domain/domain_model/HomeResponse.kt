package com.example.sharedcode.domain.domain_model

import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize

@kotlinx.serialization.Serializable
@CommonParcelize
data class HomeResponse(
    val cmd: String? = null,
    val code: Int? = null,
    val `data`: Data,
    //val httpResponse: Int? = null,
    val message: String? = null,
    val success: Boolean? = null
) : CommonParcelable

@kotlinx.serialization.Serializable
@CommonParcelize
data class Data(
    val bannerDetail: BannerDetail? = null,
    val pendingTransactionDeeplink: String? = null,
    val sections: List<Section>
) : CommonParcelable

@kotlinx.serialization.Serializable
@CommonParcelize
data class BannerDetail(
    val bannerBgColor: String? = null,
    val bannerText: String? = null,
    val bannerTextColor: String? = null,
    val shouldShowCancelButton: Boolean? = null
) : CommonParcelable

@kotlinx.serialization.Serializable
@CommonParcelize
data class Section(
    val sectionIdentifier: String = "1",
    val sectionItems: List<SectionItem> = arrayListOf(),
    val sortOrder: Int = 1,
    val title: String = "title",

    val imageUrl: String = "",

    val buttonTitle: String = "text",

    val buttonBgColor: String = "FF343434",

    val subTitle: String = ""
): CommonParcelable

@kotlinx.serialization.Serializable
@CommonParcelize
data class SectionItem(

    val buttonBgColor: String = "FF343434",

    val buttonTitle: String = "",

    val deeplink: String = "",

    val id: Int = -1,

    val imageUrl: String? = "",

    val isExternalLink: String? = "",

    val shouldShowButton: Int? = -1,

    val subtitle: String? = "",

    val title: String? = ""
): CommonParcelable

fun HomeResponse.asDomainModel(): List<Home> {
    return this.data.sections.map {
        Home(
            sectionIdentifier = it.sectionIdentifier,
            sectionItems = it.sectionItems,
            sortOrder = it.sortOrder,
            title = it.title,
            imageUrl = it.imageUrl,
            buttonTitle = it.buttonTitle,
            buttonBgColor = it.buttonBgColor,
            subTitle = it.subTitle
        )

    }
}