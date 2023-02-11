package com.example.sharedcode.domain.domain_model


import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class LoginPopup(
    @SerialName("title")
    var title: String? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("button_title")
    var buttonTitle: String? = null
) : CommonParcelable