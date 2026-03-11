package com.example.entertainhub.data.remote.dto.mapper

import com.example.entertainhub.data.model.GenreData
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.model.MoviesResponse
import com.example.entertainhub.data.model.MoviesResponseDates
import com.example.entertainhub.data.remote.dto.MovieDto
import com.example.entertainhub.data.remote.dto.MoviesResponseDto

enum class ImageUseCase {
    LIST,
    DETAIL
}

object MovieMapper {
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val POSTER_SIZE_SMALL = "w185"
    private const val POSTER_SIZE_MEDIUM = "w500"

    private const val BACKDROP_SIZE = "original"

    fun fromDto(dto: MovieDto, useCase: ImageUseCase = ImageUseCase.LIST): Movie {
        val posterSize = when (useCase) {
            ImageUseCase.LIST -> POSTER_SIZE_SMALL
            ImageUseCase.DETAIL -> POSTER_SIZE_MEDIUM
        }
        return Movie(
            id = dto.id,
            title = dto.title,
            overview = dto.overview,
            posterUrl = buildImageUrl(dto.posterPath, posterSize),
            backdropUrl = buildImageUrl(dto.backdropPath, BACKDROP_SIZE),
            releaseDate = dto.releaseDate,
            rating = dto.voteAverage.toFloat(),
            genres = GenreData.getMovieGenreNames(dto.genreIds),
            originalLanguage = dto.originalLanguage
        )
    }

    fun fromDtoList(dtos: List<MovieDto>, useCase: ImageUseCase = ImageUseCase.LIST): List<Movie> {
        return dtos.map { fromDto(dto = it, useCase = useCase) }
    }

    fun fromResponseDto(
        dto: MoviesResponseDto,
        useCase: ImageUseCase = ImageUseCase.LIST
    ): MoviesResponse {
        return MoviesResponse(
            page = dto.page,
            results = fromDtoList(dtos = dto.results, useCase = useCase),
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