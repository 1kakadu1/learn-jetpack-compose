package com.example.entertainhub.data.model

import com.example.entertainhub.data.remote.dto.MovieDto
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val rating: Float,
    val genres: List<String>,
    val originalLanguage: String
)

data class MoviesResponseDates(
    val maximum: String,
    val minimum: String
)

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
    val dates: MoviesResponseDates? = null
)