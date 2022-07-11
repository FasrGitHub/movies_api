package com.example.moviesapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesapi.databinding.ActivityMoviesBinding
import com.example.moviesapi.presentation.adapters.MovieAdapter
import com.example.moviesapi.presentation.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity() {

//    private lateinit var viewModel: MovieViewModel

    private val binding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModelCreator { MovieViewModel(application) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = MovieAdapter()
        binding.rvMoviesList.adapter = adapter
//        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        observeMovies(adapter)
    }

    private fun observeMovies(adapter: MovieAdapter) {
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}