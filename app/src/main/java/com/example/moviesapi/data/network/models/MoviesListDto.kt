package com.example.moviesapi.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    @SerializedName("results")
    @Expose
    val results: List<MovieDto>?
)
