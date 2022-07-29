package com.example.moviesapi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.data.repository.MoviesRepositoryImpl
import com.example.moviesapi.domain.usecases.ClearDatabaseUseCase
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    private val clearDatabaseUseCase = ClearDatabaseUseCase(repository)

    suspend fun clearDatabase() {
        viewModelScope.launch {
            clearDatabaseUseCase()
        }
    }
}