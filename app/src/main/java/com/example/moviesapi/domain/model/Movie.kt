package com.example.moviesapi.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
)