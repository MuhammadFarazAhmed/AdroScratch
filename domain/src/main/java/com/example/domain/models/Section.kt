package com.example.domain.models

data class Section(
    val section_identifier: String,
    val section_items: List<SectionItem>,
    val sort_order: Int,
    val sub_title: String = "",
    val image_url: String = "",
    val title: String = "",
    val button_title: String = "",
    val button_bg_color: String = "acccbc"
)