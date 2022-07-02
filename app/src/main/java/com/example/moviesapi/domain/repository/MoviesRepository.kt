package com.example.moviesapi.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.moviesapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getAllMovies(): LiveData<List<Movie>>

    suspend fun clearDatabase()

    suspend fun loadMovies(loadPosition: Int)
}