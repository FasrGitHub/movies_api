package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository

class GetPagedMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke() = moviesRepository.getPagedMovies()
}