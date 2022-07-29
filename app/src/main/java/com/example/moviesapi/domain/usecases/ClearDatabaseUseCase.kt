package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository
import javax.inject.Inject


class ClearDatabaseUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke() = moviesRepository.clearDatabase()
}