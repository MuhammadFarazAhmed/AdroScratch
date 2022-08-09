package com.example.adro.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.example.adro.R

// Set of Material typography styles to start with

val Emad = FontFamily(Font(R.font.emad_diana_extra_normal),
        Font(R.font.emad_diana_extra_regular, FontWeight.W500))

val Typography = Typography(
        h1 = TextStyle(fontFamily = Emad,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp),
        body1 = TextStyle(fontFamily = Emad,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp)
        /* Other default text styles to override
        button = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        */)