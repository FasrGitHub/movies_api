package com.example.moviesapi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.data.repository.MoviesRepositoryImpl
import com.example.moviesapi.domain.usecases.ClearDatabaseUseCase
import com.example.moviesapi.domain.usecases.LoadMoviesUseCase
import kotlinx.coroutines.launch
import java.text.ParsePosition

class SplashViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    private val loadMoviesUseCase = LoadMoviesUseCase(repository)
    private val clearDatabaseUseCase = ClearDatabaseUseCase(repository)

    suspend fun clearDatabase() {
        viewModelScope.launch {
            clearDatabaseUseCase()
        }
    }

    suspend fun loadMovies(loadPosition: Int) {
        viewModelScope.launch {
            loadMoviesUseCase(loadPosition)
        }
    }

}