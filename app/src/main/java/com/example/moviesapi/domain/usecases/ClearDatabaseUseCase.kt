package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository


class ClearDatabaseUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke() = moviesRepository.clearDatabase()
}