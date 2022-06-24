package com.example.moviesapi.data.network

import com.example.moviesapi.data.network.models.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reviews/all.json")
    fun getAllMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "M3rOquQpOjemNM6VA1sGb5TpJpf4cFJU"
    ): MoviesListDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
    }
}