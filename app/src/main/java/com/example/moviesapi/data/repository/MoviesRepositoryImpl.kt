package com.example.moviesapi.data.repository

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesapi.R
import com.example.moviesapi.data.database.MovieDao
import com.example.moviesapi.data.mapper.MovieMapper
import com.example.moviesapi.data.network.ApiFactory.apiService
import com.example.moviesapi.data.network.MoviesPageLoader
import com.example.moviesapi.data.network.MoviesPagingSource
import com.example.moviesapi.data.network.models.MoviesDtoList
import com.example.moviesapi.domain.model.Movie
import com.example.moviesapi.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val application: Application,
    private val movieDao: MovieDao,
    private val mapper: MovieMapper,
) : MoviesRepository {

    override suspend fun loadMovies(loadPosition: Int) {
        val moviesDtoList = apiService.getAllMovies(
            application.getString(R.string.token),
            loadPosition
        )
        addMovieToDb(moviesDtoList)
    }

    override suspend fun clearDatabase() {
        movieDao.deleteAllMovies()
        movieDao.truncateTable()
    }

    override fun getPagedMovies(): Flow<PagingData<Movie>> {
        val loader: MoviesPageLoader = { pageIndex, pageSize ->
            getMovies(pageIndex, pageSize)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { MoviesPagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    private suspend fun getMovies(pageIndex: Int, pageSize: Int): List<Movie> {
        loadMovies(pageIndex * 20)

        val offset = pageIndex * pageSize
        val moviesDbModel = movieDao.getAllMovies(pageSize, offset)
        val moviesList = moviesDbModel.map {
            mapper.mapDbModelToEntity(it)
        }
        return moviesList
    }

    private suspend fun addMovieToDb(moviesDtoList: MoviesDtoList) {
        moviesDtoList.results?.map {
            movieDao.addMovie(mapper.mapDtoToDbModel(it))
        }
    }

    private companion object {
        private const val PAGE_SIZE = 20
    }
}