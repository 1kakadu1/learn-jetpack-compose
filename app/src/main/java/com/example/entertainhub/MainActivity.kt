package com.example.entertainhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.entertainhub.ui.navigation.LocalNavController
import com.example.entertainhub.ui.navigation.NavGraph
import com.example.entertainhub.ui.theme.EntertainHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EntertainHubTheme {
                val navController = rememberNavController()
                val controller = WindowInsetsControllerCompat(window, window.decorView)
                controller.isAppearanceLightStatusBars = false
                CompositionLocalProvider(LocalNavController provides navController) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}