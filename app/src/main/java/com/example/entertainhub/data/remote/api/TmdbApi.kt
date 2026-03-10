package com.example.entertainhub.data.remote.api

import com.example.entertainhub.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MoviesResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "ISO-3166-1 code",
    ): MoviesResponseDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "",
    ): MoviesResponseDto

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("primary_release_year") primaryReleaseYear: String = "",
        @Query("year") year: String = "",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("region") region: String = "ISO-3166-1 code",
    ): MoviesResponseDto
}