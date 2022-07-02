package com.example.moviesapi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.moviesapi.data.repository.MoviesRepositoryImpl
import com.example.moviesapi.domain.usecases.GetAllMoviesUseCase

class MovieViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    private val getAllMoviesUseCase = GetAllMoviesUseCase(repository)

    val moviesList = getAllMoviesUseCase()
}