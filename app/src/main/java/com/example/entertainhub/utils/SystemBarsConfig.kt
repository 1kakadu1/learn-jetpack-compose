package com.example.entertainhub.utils


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetSystemBarsColor(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    darkIcons: Boolean = false,
    restoreToTheme: Boolean = true
) {
    val view = LocalView.current
    val isInPreview = LocalInspectionMode.current
    val themeBackground = MaterialTheme.colorScheme.background
    val isSystemInDarkTheme = isSystemInDarkTheme()

    if (isInPreview) return

    DisposableEffect(statusBarColor, navigationBarColor, darkIcons) {
        val activity = view.context as? Activity ?: return@DisposableEffect onDispose { }

        val window = activity.window
        val insetsController = WindowCompat.getInsetsController(window, view)

        val originalStatusBarColor = window.statusBarColor
        val originalNavigationBarColor = window.navigationBarColor
        val originalLightStatusBars = insetsController.isAppearanceLightStatusBars
        val originalLightNavigationBars = insetsController.isAppearanceLightNavigationBars

        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()

        insetsController.apply {
            isAppearanceLightStatusBars = darkIcons
            isAppearanceLightNavigationBars = darkIcons
        }

        onDispose {
            if (restoreToTheme) {
                window.statusBarColor = themeBackground.toArgb()
                window.navigationBarColor = Color.Black.toArgb()

                insetsController.apply {
                    isAppearanceLightStatusBars = !isSystemInDarkTheme
                    isAppearanceLightNavigationBars = !isSystemInDarkTheme
                }
            } else {
                window.statusBarColor = originalStatusBarColor
                window.navigationBarColor = originalNavigationBarColor

                insetsController.apply {
                    isAppearanceLightStatusBars = originalLightStatusBars
                    isAppearanceLightNavigationBars = originalLightNavigationBars
                }
            }
        }
    }
}