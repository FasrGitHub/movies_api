package com.example.moviesapi.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapi.domain.model.Movie
import com.example.moviesapi.domain.usecases.GetPagedMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getPagedMoviesUseCase: GetPagedMoviesUseCase,
) : ViewModel() {

    val moviesFlow: Flow<PagingData<Movie>> = getPagedMoviesUseCase().cachedIn(viewModelScope)
}