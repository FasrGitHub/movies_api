package com.example.moviesapi.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesapi.databinding.ActivitySplashBinding
import com.example.moviesapi.presentation.viewmodels.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        lifecycleScope.launch {
            viewModel.clearDatabase()
//            viewModel.loadMovies(PAGE_AT_INITIALIZATION)
            delay(SPLASH_DISPLAY_DELAY)
            onMoviesLoad()
        }
    }

    private fun onMoviesLoad() {
        startActivity(
            Intent(this, MovieActivity::class.java)
        )
    }

    companion object {
        private const val SPLASH_DISPLAY_DELAY: Long = 2000
        private const val PAGE_AT_INITIALIZATION = 0
    }
}