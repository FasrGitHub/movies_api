package com.example.moviesapi.data.network

import com.example.moviesapi.data.network.models.MoviesDtoList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reviews/all.json")
    suspend fun getAllMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_PARAM_OFFSET) offset: Int,
    ): MoviesDtoList

    companion object {
        private const val QUERY_PARAM_API_KEY = "api-key"
        private const val QUERY_PARAM_OFFSET = "offset"
    }
}