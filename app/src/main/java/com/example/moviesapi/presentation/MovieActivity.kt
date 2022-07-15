package com.example.moviesapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.moviesapi.databinding.ActivityMoviesBinding
import com.example.moviesapi.presentation.adapters.MovieAdapter
import com.example.moviesapi.presentation.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModelCreator { MovieViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = MovieAdapter()
        binding.rvMoviesList.adapter = adapter
        observeMovies(adapter)

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                states ->
                if (states.append is LoadState.Error) {
                    binding.button.visibility = View.VISIBLE
                    binding.button.isEnabled = true
                    binding.button.isClickable = true
                } else if (binding.button.visibility == View.VISIBLE && states.append is LoadState.NotLoading) {
                    binding.button.visibility = View.INVISIBLE
                    binding.rvMoviesList.smoothScrollBy(0, 500)
                }
            }
        }

        binding.button.setOnClickListener {
            binding.button.isEnabled = false
            binding.button.isClickable = false

            adapter.retry()
        }
    }

    private fun observeMovies(adapter: MovieAdapter) {
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}