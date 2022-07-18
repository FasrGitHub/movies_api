package com.example.moviesapi.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.example.moviesapi.R
import com.example.moviesapi.databinding.MovieInfoBinding
import com.example.moviesapi.domain.model.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        with(holder.binding) {
            if (movie != null) {
                tvNameMovie.text = movie.name
                tvDescriptionMovie.text = movie.description
                loadMovieBanner(ivBannerMovie, movie.imageUrl)
            }
        }
    }

    private fun loadMovieBanner(imageView: ImageView, url: String) {
        val context = imageView.context
        if (url.isNotBlank()) {
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.no_banner)
                .error(R.drawable.no_banner)
                .into(imageView)
        } else {
            Glide.with(context)
                .load(R.drawable.no_banner)
                .into(imageView)
        }
    }
}