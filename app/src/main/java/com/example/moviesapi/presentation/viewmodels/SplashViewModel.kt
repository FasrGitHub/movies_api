package com.example.moviesapi.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.domain.usecases.ClearDatabaseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val clearDatabaseUseCase: ClearDatabaseUseCase,
) : ViewModel() {

    suspend fun clearDatabase() {
        viewModelScope.launch {
            clearDatabaseUseCase()
        }
    }
}