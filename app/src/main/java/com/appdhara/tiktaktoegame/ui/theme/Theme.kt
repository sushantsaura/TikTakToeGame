package com.appdhara.tiktaktoegame.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun TikTakToeApp(){
    TikTakToeGameTheme(
        darkTheme = true
    ) {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}
@Composable
fun TikTakToeGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) {
        darkColorScheme(
            primary = Color(0xFF6366F1),
            secondary = Color(0xFFEC4899),
            background = Color(0xFF0F172A),
            surface = Color(0xFF202F41),
            onBackground = Color.White,
            onSurface = Color.White,
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6366F1),
            secondary = Color(0xFFEC4899),
            background = Color(0xFFF8FAFC),
            surface = Color.White,
            onBackground = Color(0xFF0F172A),
            onSurface = Color(0xFF0F172A),
        )
    }

    MaterialTheme(
      colorScheme = colorScheme,
      content = content
    )
}