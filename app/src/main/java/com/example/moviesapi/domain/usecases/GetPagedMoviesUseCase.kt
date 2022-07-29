package com.example.moviesapi.domain.usecases

import com.example.moviesapi.domain.repository.MoviesRepository
import javax.inject.Inject

class GetPagedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke() = moviesRepository.getPagedMovies()
}