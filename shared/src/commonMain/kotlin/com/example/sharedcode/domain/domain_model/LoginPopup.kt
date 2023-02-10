package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class LoginPopup(
    @SerialName("title")
    val title: String? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("button_title")
    val buttonTitle: String? = null
) : CommonParcelable