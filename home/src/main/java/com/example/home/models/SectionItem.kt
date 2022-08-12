package com.example.home.ui.models

data class SectionItem(
    val button_bg_color: String,
    val button_title: String,
    val deeplink: String,
    val id: Int,
    val image_url: String,
    val is_external_link: Int,
    val should_show_button: Int,
    val subtitle: String,
    val title: String
)