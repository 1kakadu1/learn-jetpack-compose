package com.example.entertainhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.entertainhub.di.AppContainer
import com.example.entertainhub.di.LocalAppContainer
import com.example.entertainhub.ui.navigation.NavGraph
import com.example.entertainhub.ui.theme.EntertainHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val appContainer = AppContainer(applicationContext)
        setContent {
            CompositionLocalProvider(LocalAppContainer provides appContainer) {
                EntertainHubTheme {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}