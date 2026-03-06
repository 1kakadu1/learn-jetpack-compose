package com.example.entertainhub.data.remote.dto.mapper

import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.model.MoviesResponse
import com.example.entertainhub.data.model.MoviesResponseDates
import com.example.entertainhub.data.remote.dto.MovieDto
import com.example.entertainhub.data.remote.dto.MoviesResponseDto

object MovieMapper {
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val POSTER_SIZE = "w500"
    private const val BACKDROP_SIZE = "original"

    fun fromDto(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.title,
            overview = dto.overview,
            posterUrl = buildImageUrl(dto.posterPath, POSTER_SIZE),
            backdropUrl = buildImageUrl(dto.backdropPath, BACKDROP_SIZE),
            releaseDate = dto.releaseDate,
            rating = dto.voteAverage.toFloat(),
            genres = dto.genreIds,
            originalLanguage = dto.originalLanguage
        )
    }

    fun fromDtoList(dtos: List<MovieDto>): List<Movie> {
        return dtos.map { fromDto(it) }
    }

    fun fromResponseDto(dto: MoviesResponseDto): MoviesResponse {
        return MoviesResponse(
            page = dto.page,
            results=fromDtoList(dto.results),
            totalPages = dto.totalPages,
            totalResults = dto.totalResults,
            dates = dto.dates?.let {
                MoviesResponseDates(
                    maximum = it.maximum,
                    minimum = it.minimum
                )
            }
        )
    }

    private fun buildImageUrl(path: String?, size: String): String {
        return if (path != null) {
            "$IMAGE_BASE_URL$size$path"
        } else {
            ""
        }
    }
}