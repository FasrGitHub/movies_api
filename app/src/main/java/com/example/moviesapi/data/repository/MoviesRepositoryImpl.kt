package com.example.moviesapi.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moviesapi.R
import com.example.moviesapi.data.database.MovieDatabase
import com.example.moviesapi.data.mapper.MovieMapper
import com.example.moviesapi.data.network.ApiFactory.apiService
import com.example.moviesapi.data.network.models.MoviesDtoList
import com.example.moviesapi.domain.model.Movie
import com.example.moviesapi.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val application: Application
) : MoviesRepository {

    private val movieDao = MovieDatabase.getInstance(application).movieDao()
    private val mapper = MovieMapper()

    override suspend fun loadMovies(loadPosition: Int) {
        try {
            val moviesDtoList = apiService.getAllMovies(
                application.getString(R.string.token),
                loadPosition
            )
            addMovieToDb(moviesDtoList)
        } catch (e: Exception) {
            Log.d("MoviesRepositoryImpl", "loadDataERROR")
        }
    }

    override suspend fun clearDatabase() {
        movieDao.deleteAllMovies()
        movieDao.truncateTable()
    }

    override fun getAllMovies(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getAllMovies()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    private suspend fun addMovieToDb(moviesDtoList: MoviesDtoList) {
        moviesDtoList.results?.map {
            movieDao.addMovie(mapper.mapDtoToDbModel(it))
        }
    }
}