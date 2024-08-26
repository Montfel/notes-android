package com.montfel.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    onPrimary = LightGray,
    primaryContainer = Blue,
    onPrimaryContainer = LightGray,
    background = LightGray,
    onBackground = Color.Black,
    secondary = Gray
)

@Composable
fun NotesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}