package com.example.moviesapi.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_list")
data class MovieDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val imageUrl: String,
)