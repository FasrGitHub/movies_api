package com.example.moviesapi.presentation

import android.app.Application
import com.example.moviesapi.di.DaggerApplicationComponent

class MoviesApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}