package com.example.entertainhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.entertainhub.ui.screens.home.HomeScreen
import com.example.entertainhub.ui.theme.EntertainHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EntertainHubTheme {
                HomeScreen()
            }
        }
    }
}