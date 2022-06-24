package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository

class GetAllMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke() = moviesRepository.getAllMovies()
}