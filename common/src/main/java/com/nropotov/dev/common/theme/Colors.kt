package com.nropotov.dev.common.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

class Colors(
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
)

val black = Color(0xFF000000)
val smoke_grey = Color(0xFF464646)
val white_crema = Color(0xFFF0F0F0)
val white = Color(0xFFFFFFFF)
val black_night = Color(0xFF000000)
val smoke_black = Color(0xFF191919)

val darkColorScheme = Colors(
    primaryTextColor = white,
    secondaryTextColor = smoke_grey,
    primaryBackgroundColor = black_night,
    secondaryBackgroundColor = smoke_black
)

val lightColorScheme = Colors(
    primaryTextColor = black,
    secondaryTextColor = smoke_grey,
    primaryBackgroundColor = white,
    secondaryBackgroundColor = white_crema
)

@Composable
fun rememberThemeColors(isDarkTheme: Boolean) = remember {
    if (isDarkTheme) darkColorScheme else lightColorScheme
}


