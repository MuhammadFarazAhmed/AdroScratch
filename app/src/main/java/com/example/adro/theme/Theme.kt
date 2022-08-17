package com.example.adro.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.adro.ToolbarPreview

private val DarkColorPalette =
        darkColors(primary = Purple200, primaryVariant = Purple700, secondary = Red)

private val LightColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Red,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.Black,
        onSurface = Color.Black,
                                           )

@Composable fun AdroScratchTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                                 content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}

@Preview(name = "Dark Theme" ,showBackground = true) @Composable
fun AdroScratchDarkThemePreview(darkTheme: Boolean = true) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    MaterialTheme(colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = { Scaffold(topBar = { ToolbarPreview() }, content = {}) })
}

@Preview(name = "Light Theme" ,showBackground = true) @Composable
fun AdroScratchLightThemePreview(darkTheme: Boolean = false) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    
    MaterialTheme(colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = { Scaffold(topBar = { ToolbarPreview() },content = {}) })
}