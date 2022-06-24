package com.example.moviesapi.domain.repository

import androidx.lifecycle.LiveData
import com.example.moviesapi.domain.model.Movie

interface MoviesRepository {

    fun getAllMovies(): LiveData<List<Movie>>

    fun loadMovies()
}