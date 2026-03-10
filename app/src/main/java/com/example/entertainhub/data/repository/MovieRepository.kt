package com.example.entertainhub.data.repository

import android.util.Log
import com.example.entertainhub.data.model.MoviesResponse
import com.example.entertainhub.data.remote.RetrofitInstance
import com.example.entertainhub.data.remote.dto.mapper.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository {

    private val api = RetrofitInstance.api

    fun getPopularMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
        try {
            val response = api.getPopularMovies(
                language = "en-US",
                page = page
            )

            val data = MovieMapper.fromResponseDto(response)
            emit(Result.success(data))

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getTopRatedMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
        try {
            val response = api.getTopRatedMovies(
                language = "en-US",
                page = page
            )

            val data = MovieMapper.fromResponseDto(response)
            emit(Result.success(data))

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getNowPlayingMovies(page: Int = 1): Flow<Result<MoviesResponse>> = flow {
        try {
            val response = api.getNowPlayingMovies(
                language = "en-US",
                page = page
            )

            val data = MovieMapper.fromResponseDto(response)
            Log.d("tewst", data.results.size.toString())
            emit(Result.success(data))

        } catch (e: Exception) {
            emit(Result.failure(e))
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
}