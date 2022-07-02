package com.example.moviesapi.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesapi.databinding.MovieInfoBinding
import com.example.moviesapi.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

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
            with(movie) {
                tvNameMovie.text = movie.name
                tvDescriptionMovie.text = movie.description
                Picasso.get().load(imageUrl).into(ivBannerMovie)
            }
        }
    }
}