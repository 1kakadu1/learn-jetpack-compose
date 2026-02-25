package com.example.entertainhub.data.repository

import android.util.Log
import com.example.entertainhub.data.model.Movie
import com.example.entertainhub.data.remote.RetrofitInstance
import com.example.entertainhub.data.remote.dto.mapper.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepository {

    private val api = RetrofitInstance.api

    fun getPopularMovies(page: Int = 1): Flow<Result<List<Movie>>> = flow {
        try {
            val response = api.getPopularMovies(
                language = "en-US",
                page = page
            )

            val movies = MovieMapper.fromDtoList(response.results)
            emit(Result.success(movies))

        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)  // ← Выполнять в IO потоке (не блокировать UI
}