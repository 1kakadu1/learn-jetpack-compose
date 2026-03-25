package com.example.entertainhub.data.local.dao

import androidx.room.*
import com.example.entertainhub.data.local.entity.MovieEntity
import com.example.entertainhub.data.local.entity.MovieListType
import kotlinx.coroutines.flow.Flow

// data/local/dao/MovieDao.kt
@Dao
interface MovieDao {

    @Query(
        """
        SELECT * FROM movies 
        WHERE listType = :listType 
        ORDER BY page ASC, position ASC
    """
    )
    fun getMoviesByListType(listType: MovieListType): Flow<List<MovieEntity>>

    @Query(
        """
        SELECT * FROM movies 
        WHERE listType = :listType AND page = :page 
        ORDER BY position ASC
    """
    )
    suspend fun getPage(listType: MovieListType, page: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies WHERE listType = :listType AND page = :page")
    suspend fun deletePage(listType: MovieListType, page: Int)

    @Query("DELETE FROM movies WHERE listType = :listType")
    suspend fun deleteListType(listType: MovieListType)

    @Query("SELECT COALESCE(MAX(page), 0) FROM movies WHERE listType = :listType")
    suspend fun getMaxPage(listType: MovieListType): Int

    @Query("SELECT COUNT(*) > 0 FROM movies WHERE listType = :listType AND page = :page")
    suspend fun hasPage(listType: MovieListType, page: Int): Boolean

    @Query("DELETE FROM movies WHERE cachedAt < :timestamp")
    suspend fun deleteExpired(timestamp: Long)

    @Query("SELECT MIN(cachedAt) FROM movies WHERE listType = :listType AND page = :page")
    suspend fun getPageCacheTime(listType: MovieListType, page: Int): Long?
}