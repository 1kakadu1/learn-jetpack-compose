package com.example.entertainhub.di

import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.entertainhub.data.local.AppDatabase
import com.example.entertainhub.data.repository.MovieRepository

class AppContainer(context: Context) {
    val database = AppDatabase.getInstance(context)
    val movieRepository = MovieRepository(database.movieDao())
}

val LocalAppContainer = staticCompositionLocalOf<AppContainer> {
    error("AppContainer not provided")
}