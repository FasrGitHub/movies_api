package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository

class LoadMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(loadPosition: Int) = moviesRepository.loadMovies(loadPosition)
}