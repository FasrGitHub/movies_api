package com.example.moviesapi.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapi.domain.model.Movie

typealias MoviesPageLoader = suspend (pageIndex: Int, pageSize: Int) -> List<Movie>

class MoviesPagingSource(
    private val loader: MoviesPageLoader
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(20) ?: page.nextKey?.minus(20)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: 0

        return try {

            val movies = loader.invoke(pageIndex, params.loadSize)

            return LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 0) null else pageIndex - 20,
                nextKey = if (movies.size == params.loadSize) pageIndex + 20 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}