package com.example.moviesapi.di

import android.app.Application
import com.example.moviesapi.data.database.MovieDao
import com.example.moviesapi.data.database.MovieDatabase
import com.example.moviesapi.data.repository.MoviesRepositoryImpl
import com.example.moviesapi.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideMoviesDao(
            application: Application
        ): MovieDao {
            return MovieDatabase.getInstance(application).movieDao()
        }
    }
}