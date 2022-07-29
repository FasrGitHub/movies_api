package com.example.moviesapi.di

import android.app.Application
import com.example.moviesapi.presentation.MovieActivity
import com.example.moviesapi.presentation.SplashActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject (activity: SplashActivity)

    fun inject (activity: MovieActivity)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}