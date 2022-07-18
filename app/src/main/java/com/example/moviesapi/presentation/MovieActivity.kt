package com.example.moviesapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapi.databinding.ActivityMoviesBinding
import com.example.moviesapi.presentation.adapters.DefaultLoadStateAdapter
import com.example.moviesapi.presentation.adapters.MovieAdapter
import com.example.moviesapi.presentation.adapters.TryAgainAction
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
//        val adapter = MovieAdapter()
//        binding.rvMoviesList.adapter = adapter

        setupMoviesList()

//        lifecycleScope.launch {
//            adapter.loadStateFlow.collectLatest {
//                states ->
//                if (states.append is LoadState.Error) {
//                    binding.clError.visibility = View.VISIBLE
//                    binding.buttonTryAgain.visibility = View.VISIBLE
//                    binding.buttonTryAgain.isEnabled = true
//                    binding.buttonTryAgain.isClickable = true
//                    binding.tvErrorManyRequests.visibility = View.VISIBLE
//                } else if (binding.buttonTryAgain.visibility == View.VISIBLE && states.append is LoadState.NotLoading) {
//                    binding.clError.visibility = View.INVISIBLE
//                    binding.buttonTryAgain.visibility = View.INVISIBLE
//                    binding.tvErrorManyRequests.visibility = View.INVISIBLE
//                    binding.rvMoviesList.smoothScrollBy(0, 500)
//                }
//            }
//        }
//
//        binding.buttonTryAgain.setOnClickListener {
//            binding.buttonTryAgain.isEnabled = false
//            binding.buttonTryAgain.isClickable = false
//
//            adapter.retry()
//        }
    }

    private fun setupMoviesList() {
        val adapter = MovieAdapter()

        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.rvMoviesList.layoutManager = LinearLayoutManager(this)
        binding.rvMoviesList.adapter = adapterWithLoadState

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