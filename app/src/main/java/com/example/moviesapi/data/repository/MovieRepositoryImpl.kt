package com.example.moviesapi.data.repository

import androidx.lifecycle.LiveData
import com.example.moviesapi.domain.model.Movie
import com.example.moviesapi.domain.repository.MoviesRepository

class MovieRepositoryImpl : MoviesRepository {

    override fun getAllMovies(): LiveData<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun loadMovies() {
        TODO("Not yet implemented")
    }
}