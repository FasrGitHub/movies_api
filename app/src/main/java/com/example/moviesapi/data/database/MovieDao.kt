package com.example.moviesapi.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_list")
    fun getAllProductsCart(): LiveData<List<MovieDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieDbModel: MovieDbModel)
}