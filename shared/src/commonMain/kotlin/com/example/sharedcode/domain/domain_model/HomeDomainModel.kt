package com.example.sharedcode.domain.domain_model

import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize


@CommonParcelize
data class Home(
    val sectionIdentifier: String = "1",

    val sectionItems: List<HomeItem> = arrayListOf(),

    val sortOrder: Int = 1,

    val title: String = "title",

    val imageUrl: String = "",

    val buttonTitle: String = "text",

    val buttonBgColor: String = "FF343434",

    val subTitle: String = ""
) : CommonParcelable


@CommonParcelize
data class HomeItem(

    val buttonBgColor: String = "FF343434",

    val buttonTitle: String = "",

    val deeplink: String = "",

    val id: Int = -1,

    val imageUrl: String? = "",

    val isExternalLink: String? = "",

    val shouldShowButton: Int? = -1,

    val subtitle: String? = "",

    val title: String? = ""
) : CommonParcelable
