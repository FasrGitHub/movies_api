package com.example.moviesapi.di

import androidx.lifecycle.ViewModel
import com.example.moviesapi.presentation.viewmodels.MovieViewModel
import com.example.moviesapi.presentation.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel
}