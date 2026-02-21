package com.example.entertainhub.data.model

data class MovieDetailData(
    val id: String,
    val slug: String,
    val name: String,
    val category: String,
    val quality: String,
    val image: String,
    val images: List<String>,
    val description: String,
    val duration: String,
    val date: String,
    val language: String,
)