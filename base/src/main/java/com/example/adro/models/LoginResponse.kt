package com.example.adro.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null
) {
    @Serializable
    data class Data(
        @SerialName("user_profile")
        val user: User? = null
    ) {
        @Serializable
        data class User(
            @SerialName("message")
            val message: String? = null,
            @SerialName("user_id")
            val userId: Int? = null,
            @SerialName("session_token")
            val sessionToken: String? = null,
            @SerialName("member_type")
            val memberType: Int? = null,
            @SerialName("currency")
            val currency: String? = null,
            @SerialName("new_user")
            val newUser: Boolean? = null,
            @SerialName("device_install_token")
            val deviceInstallToken: String? = null,
            @SerialName("device_uid")
            val deviceUid: String? = null,
            @SerialName("device_os")
            val deviceOs: String? = null,
            @SerialName("device_model")
            val deviceModel: String? = null,
            @SerialName("user_group")
            val userGroup: Int? = null,
            @SerialName("user_group_code")
            val userGroupCode: String? = null,
            @SerialName("user_group_logo")
            val userGroupLogo: String? = null,
            @SerialName("number_of_offers")
            val numberOfOffers: Int? = null,
            @SerialName("is_demographics_updated")
            val isDemographicsUpdated: Boolean? = null,
            @SerialName("first_name")
            val firstName: String? = null,
            @SerialName("last_name")
            val lastName: String? = null,
            @SerialName("email")
            val email: String? = null,
            @SerialName("phone_no")
            val phoneNo: String? = null,
            @SerialName("gender")
            val gender: String? = null,
            @SerialName("nationality")
            val nationality: String? = null,
            @SerialName("dob")
            val dob: String? = null,
            @SerialName("profile_image")
            val profileImage: String? = null,
            @SerialName("enable_push_notification")
            val enablePushNotification: Int? = null,
            @SerialName("enable_email_notification")
            val enableEmailNotification: Int? = null,
            @SerialName("selected_visa_type")
            val selectedVisaType: Int? = null,
            @SerialName("is_visa_category_selected")
            val isVisaCategorySelected: Boolean? = null
        )
    }
}