package com.example.entertainhub.data.repository

import android.util.Log
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


class MovieRepository(private val api: TmdbApi = RetrofitInstance.api, private val dao: MovieDao) {

    companion object {
        private const val CACHE_DURATION = 1 * 60 * 60 * 1000L  // 1h
    }

    fun getPopularMovies(
        page: Int = 1,
        forceRefresh: Boolean = false
    ): Flow<Result<MoviesResponse>> = flow {
        val listType = MovieListType.POPULAR
        if (!forceRefresh) {
            val cachedMovies = dao.getPage(listType, page);
            if (cachedMovies.isNotEmpty()) {
                val movies = cachedMovies.map { it.toMovie() }
                emit(
                    Result.success(
                        MoviesResponse(
                            page = page,
                            results = movies,
                            totalPages = 100,
                            totalResults = 10000
                        )
                    )
                )
            }
        }

        try {
            val response = api.getPopularMovies(
                language = "en-US",
                page = page
            )

            val data = MovieMapper.fromResponseDto(response)
            val entities = data.results.mapIndexed { index, movie ->
                MovieEntity(
                    id = movie.id,
                    listType = listType,
                    page = page,
                    title = movie.title,
                    overview = movie.overview,
                    posterUrl = movie.posterUrl,
                    backdropUrl = movie.backdropUrl,
                    releaseDate = movie.releaseDate,
                    rating = movie.rating,
                    genres = movie.genres,
                    position = index,
                    originalLanguage = movie.originalLanguage,
                )
            }
            dao.deletePage(listType, page)
            dao.insertAll(entities)
            emit(Result.success(data))

        } catch (e: Exception) {
            //TODO Посмотреть что будет, если нет кеша
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    private fun getMoviesByType(
        listType: MovieListType,
        page: Int,
        forceRefresh: Boolean
    ): Flow<Result<MoviesResponse>> = flow {

        if (!forceRefresh) {
            val cached = dao.getPage(listType, page)
            if (cached.isNotEmpty()) {
                emit(Result.success(cached.toMoviesResponse(page)))
            }
        }

        // 2. Network update
        try {
            val apiCall = when (listType) {
                MovieListType.POPULAR -> api.getPopularMovies("en-US", page = page)
                MovieListType.TOP -> api.getTopRatedMovies("en-US", page = page)
                MovieListType.NOW_PLAYING -> api.getNowPlayingMovies("en-US", page = page)
            }

            val response = MovieMapper.fromResponseDto(apiCall)

            // Save to DB
            val entities = response.results.mapIndexed { index, movie ->
                movie.toEntity(listType, page, index)
            }

            dao.deletePage(listType, page)
            dao.insertAll(entities)

            emit(Result.success(response))

        } catch (e: Exception) {
            val cached = dao.getPage(listType, page)
            if (cached.isEmpty()) {
                emit(Result.failure(e))
            }
        }

    }.flowOn(Dispatchers.IO)

    suspend fun deleteExpiredCache(timestamp: Long) {
        dao.deleteExpired(timestamp)
    }

    //    fun getTopRatedMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
//        try {
//            val response = api.getTopRatedMovies(
//                language = "en-US",
//                page = page
//            )
//
//            val data = MovieMapper.fromResponseDto(response)
//            emit(Result.success(data))
//
//        } catch (e: Exception) {
//            emit(Result.failure(e))
//        }
//    }.flowOn(Dispatchers.IO)
//
//    fun getNowPlayingMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
//        try {
//            val response = api.getNowPlayingMovies(
//                language = "en-US",
//                page = page
//            )
//
//            val data = MovieMapper.fromResponseDto(response)
//            Log.d("tewst", data.results.size.toString())
//            emit(Result.success(data))
//
//        } catch (e: Exception) {
//            emit(Result.failure(e))
//        }
//    }.flowOn(Dispatchers.IO)
    fun getTopRatedMovies(page: Int = 1, forceRefresh: Boolean = false) =
        getMoviesByType(MovieListType.TOP, page, forceRefresh)

    fun getNowPlayingMovies(page: Int = 1, forceRefresh: Boolean = false) =
        getMoviesByType(MovieListType.NOW_PLAYING, page, forceRefresh)

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
}

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