package com.example.entertainhub.data.repository

import com.example.entertainhub.BuildConfig
import com.example.entertainhub.data.local.dao.MovieDao
import com.example.entertainhub.data.local.entity.MovieEntity
import com.example.entertainhub.data.local.entity.MovieListType
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.model.MoviesResponse
import com.example.entertainhub.data.remote.RetrofitInstance
import com.example.entertainhub.data.remote.api.TmdbApi
import com.example.entertainhub.data.remote.dto.mapper.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val dao: MovieDao,
    private val api: TmdbApi = RetrofitInstance.api
) {

    companion object {
        private const val CACHE_DURATION = 1 * 60 * 60 * 1000L  // 1h
    }

    fun getPopularMovies(page: Int = 1, forceRefresh: Boolean = false) =
        getMoviesByType(MovieListType.POPULAR, page, forceRefresh)

    fun getTopRatedMovies(page: Int = 1, forceRefresh: Boolean = false) =
        getMoviesByType(MovieListType.TOP, page, forceRefresh)

    fun getNowPlayingMovies(page: Int = 1, forceRefresh: Boolean = false) =
        getMoviesByType(MovieListType.NOW_PLAYING, page, forceRefresh)

    private fun getMoviesByType(
        listType: MovieListType,
        page: Int,
        forceRefresh: Boolean
    ): Flow<Result<MoviesResponse>> = flow {

        var hasCachedData = false

        if (!forceRefresh) {
            val cached = dao.getPage(listType, page)
            if (cached.isNotEmpty()) {
                hasCachedData = true
                emit(Result.success(cached.toMoviesResponse(page)))
            }
        }

        try {
            val responseDto = when (listType) {
                MovieListType.POPULAR -> api.getPopularMovies(
                    language = "en-US",
                    page = page
                )

                MovieListType.TOP -> api.getTopRatedMovies(
                    language = "en-US",
                    page = page
                )

                MovieListType.NOW_PLAYING -> api.getNowPlayingMovies(
                    language = "en-US",
                    page = page
                )
            }

            val response = MovieMapper.fromResponseDto(responseDto)

            // ✅ Шаг 3: Сохраняем в БД
            val entities = response.results.mapIndexed { index, movie ->
                movie.toEntity(listType, page, index)
            }

            dao.deletePage(listType, page)
            dao.insertAll(entities)

            // ✅ Шаг 4: Эмитим свежие данные
            emit(Result.success(response))

        } catch (e: Exception) {
            // ✅ При ошибке: если не эмитили кеш — эмитим ошибку
            if (!hasCachedData) {
                // Попытка вернуть stale cache
                val staleCache = dao.getPage(listType, page)
                if (staleCache.isNotEmpty()) {
                    emit(Result.success(staleCache.toMoviesResponse(page)))
                } else {
                    emit(Result.failure(e))
                }
            }
            // Если уже эмитили кеш — молча игнорируем ошибку
        }

    }.flowOn(Dispatchers.IO)

    fun getSearchMovies(query: String): Flow<Result<MoviesResponse>> = flow {
        try {
            val response = api.getSearchMovies(
                language = "en-US",
                query = query
            )

            val data = MovieMapper.fromResponseDto(response)
            emit(Result.success(data))

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun deleteExpiredCache(timestamp: Long) {
        dao.deleteExpired(timestamp)
    }
}

// Extensions
private fun List<MovieEntity>.toMoviesResponse(page: Int) = MoviesResponse(
    page = page,
    results = map { it.toMovie() },
    totalPages = 100,
    totalResults = 10000
)

private fun Movie.toEntity(listType: MovieListType, page: Int, position: Int) = MovieEntity(
    id = id,
    listType = listType,
    page = page,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    releaseDate = releaseDate,
    rating = rating,
    genres = genres,
    position = position,
    originalLanguage = originalLanguage
)

private fun MovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    releaseDate = releaseDate,
    rating = rating,
    genres = genres,
    originalLanguage = originalLanguage
)