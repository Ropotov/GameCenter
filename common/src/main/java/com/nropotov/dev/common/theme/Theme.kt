package com.nropotov.dev.common.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun GameCenterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { defaultDimens }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    CompositionLocalProvider(
        staticCompositionLocalOf { dimensionSet } provides dimensionSet,
        LocalColorsProvider provides rememberThemeColors(darkTheme),
    ) {
        MaterialTheme(content = content)
    }
}

private val defaultDimens = Dimens()

private val LocalDimens = staticCompositionLocalOf { defaultDimens }

private val LocalColorsProvider = staticCompositionLocalOf<Colors> { error("No colors provided.") }

object GameCenterTheme {
    val dimens: Dimens
        @Composable
        get() = LocalDimens.current

    val colors: Colors
        @Composable
        get() = LocalColorsProvider.current
}
