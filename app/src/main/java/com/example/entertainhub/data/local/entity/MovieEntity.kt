package com.example.entertainhub.data.local.entity

import androidx.room.Entity
import androidx.room.Index

enum class MovieListType {
    POPULAR,
    TOP,
    NOW_PLAYING
}

@Entity(
    tableName = "movies",
    primaryKeys = ["id", "listType"],
    indices = [Index(value = ["listType", "page"]), Index(value = ["cachedAt"])]
)
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val rating: Float,
    val genres: List<String>,
    val listType: MovieListType,
    val page: Int,
    val position: Int,
    val originalLanguage: String,
    val cachedAt: Long = System.currentTimeMillis()
)