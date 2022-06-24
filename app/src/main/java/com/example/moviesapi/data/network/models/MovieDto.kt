package com.example.moviesapi.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("headline")
    @Expose
    val name: String,
    @SerializedName("summary_short")
    @Expose
    val description: String,
    @SerializedName("multimedia")
    @Expose
    val multimedia: MultimediaDto,
)
