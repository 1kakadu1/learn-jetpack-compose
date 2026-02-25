package com.example.entertainhub.data.remote.dto.mapper

import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.remote.dto.MovieDto


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
            genres = dto.genreIds
        )
    }

    fun fromDtoList(dtos: List<MovieDto>): List<Movie> {
        return dtos.map { fromDto(it) }
    }

    private fun buildImageUrl(path: String?, size: String): String {
        return if (path != null) {
            "$IMAGE_BASE_URL$size$path"
        } else {
            ""
        }
    }
}