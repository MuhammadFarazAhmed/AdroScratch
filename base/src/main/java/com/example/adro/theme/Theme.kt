package com.example.adro.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorPalette =
    darkColors(
        primary = Color.White,
        primaryVariant = Color.White,
        secondary = Red,
        background = Black,
        surface = Black,
        onPrimary = Black500,
        onSecondary = Red,
        onBackground = Color.White,
        onSurface = Color.White,
    )

private val LightColorPalette =
    lightColors(
        primary = Black500,
        primaryVariant = Black500,
        secondary = Red,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Red,
        onBackground = Black500,
        onSurface = Black500,
    )

@Composable
fun ThriveScratchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}

@Preview(name = "Dark Theme", showBackground = true)
@Composable
fun ThriveScratchDarkThemePreview(darkTheme: Boolean = true) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = { Scaffold(content = {}) }
    )
}

@Preview(name = "Light Theme", showBackground = true)
@Composable
fun ThriveScratchLightThemePreview(darkTheme: Boolean = true) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {

        })
}