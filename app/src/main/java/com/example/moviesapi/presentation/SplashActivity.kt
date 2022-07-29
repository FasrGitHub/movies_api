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
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel

    private val binding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MoviesApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]

        lifecycleScope.launch {
            viewModel.clearDatabase()
            delay(SPLASH_DISPLAY_DELAY)
            onMoviesLoad()
        }
    }

    private fun onMoviesLoad() {
        startActivity(
            Intent(this, MovieActivity::class.java)
        )
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    companion object {
        private const val SPLASH_DISPLAY_DELAY: Long = 1000
    }
}