package com.example.moviesapi.domain.repository

import androidx.paging.PagingData
import com.example.moviesapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPagedMovies(): Flow<PagingData<Movie>>

    suspend fun clearDatabase()

    suspend fun loadMovies(loadPosition: Int)
}