package com.example.entertainhub.ui.navigation

object Routes {
    const val HOME = "home"
    const val DETAILS = "details/{movieId}"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
    const val SETTINGS = "settings"

    fun details(movieId: String) = "details/$movieId"
}