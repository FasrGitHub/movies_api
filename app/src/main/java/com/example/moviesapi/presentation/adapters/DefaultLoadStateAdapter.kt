package com.example.moviesapi.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapi.databinding.PartDefaultLoadStateBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PartDefaultLoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding, tryAgainAction)
    }

    class Holder(
        private val binding: PartDefaultLoadStateBinding,
        private val tryAgainAction: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tryAgainButton.setOnClickListener { tryAgainAction() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            Log.d("DefaultLoadStateAdapter---------------->", loadState.toString())
            if (loadState.toString() == TOO_MANY_REQUESTS) {
                elementDisplay(tvTooManyRequests, loadState)
            } else if (loadState.toString() == NO_CONNECTION) {
                elementDisplay(tvNoConnection, loadState)
            } else {
                elementDisplay(tvUnexpectedError, loadState)
            }
        }

        private fun elementDisplay(
            requiredText: TextView,
            loadState: LoadState
        ) = with(binding) {
            tvNoConnection.isVisible = false
            tvTooManyRequests.isVisible = false
            tvUnexpectedError.isVisible = false
            progressBar.isVisible = loadState is LoadState.Loading
            requiredText.isVisible = loadState is LoadState.Error
            tryAgainButton.isVisible = loadState is LoadState.Error
        }

        companion object {
            private const val TOO_MANY_REQUESTS =
                "Error(endOfPaginationReached=false, error=retrofit2.HttpException: HTTP 429 Too Many Requests)"
            private const val NO_CONNECTION =
                "Error(endOfPaginationReached=false, error=java.net.UnknownHostException: Unable to resolve host \"api.nytimes.com\": No address associated with hostname)"
        }
    }
}