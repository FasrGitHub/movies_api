package com.example.moviesapi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapi.data.repository.MoviesRepositoryImpl
import com.example.moviesapi.domain.model.Movie
import com.example.moviesapi.domain.usecases.GetPagedMoviesUseCase
import kotlinx.coroutines.flow.Flow

class MovieViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MoviesRepositoryImpl(application)

    private val getPagedMoviesUseCase = GetPagedMoviesUseCase(repository)

    val moviesFlow: Flow<PagingData<Movie>> = getPagedMoviesUseCase().cachedIn(viewModelScope)
}