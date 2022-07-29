package com.example.moviesapi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesapi.databinding.ActivityMoviesBinding
import com.example.moviesapi.presentation.adapters.DefaultLoadStateAdapter
import com.example.moviesapi.presentation.adapters.MovieAdapter
import com.example.moviesapi.presentation.adapters.TryAgainAction
import com.example.moviesapi.presentation.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class MovieActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MoviesApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]
        setupMoviesList()
    }

    private fun setupMoviesList() {
        val adapter = MovieAdapter()

        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.rvMoviesList.adapter = adapterWithLoadState

        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            binding.loadStateView,
            tryAgainAction,
            this
        )

        observeMovies(adapter)
        observeLoadState(adapter)
    }

    private fun observeMovies(adapter: MovieAdapter) {
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState(adapter: MovieAdapter) {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}