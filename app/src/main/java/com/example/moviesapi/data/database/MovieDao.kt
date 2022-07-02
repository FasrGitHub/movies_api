package com.example.moviesapi.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_list")
    fun getAllMovies(): LiveData<List<MovieDbModel>>

    @Query("DELETE FROM movies_list")
    suspend fun deleteAllMovies()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'movies_list'")
    suspend fun truncateTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDbModel: MovieDbModel)
}