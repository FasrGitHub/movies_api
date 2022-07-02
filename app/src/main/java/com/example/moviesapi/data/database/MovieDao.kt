package com.example.moviesapi.data.database

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_list LIMIT :limit OFFSET :offset")
    fun getAllMovies(limit: Int, offset: Int): List<MovieDbModel>

    @Query("DELETE FROM movies_list")
    suspend fun deleteAllMovies()

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'movies_list'")
    suspend fun truncateTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDbModel: MovieDbModel)
}