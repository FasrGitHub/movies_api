package com.example.moviesapi.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MultimediaDto (
    @SerializedName("src")
    @Expose
    val imageUrl: String,
)