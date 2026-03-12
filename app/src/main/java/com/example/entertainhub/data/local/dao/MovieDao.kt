package com.example.entertainhub.data.local.dao

import androidx.room.*
import com.example.entertainhub.data.local.entity.MovieEntity
import com.example.entertainhub.data.local.entity.MovieListType
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies WHERE listType = :listType")
    fun getMoviesByListType(listType: MovieListType): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE listType = :listType")
    suspend fun deleteByListType(listType: MovieListType)

    @Query("SELECT COUNT(*) FROM movies WHERE listType = :listType")
    suspend fun getCountByListType(listType: MovieListType): Int
}