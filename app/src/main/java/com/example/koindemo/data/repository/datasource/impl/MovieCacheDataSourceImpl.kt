package com.example.koindemo.data.repository.datasource.impl

import com.example.koindemo.data.model.Movie
import com.example.koindemo.data.repository.datasource.MovieCacheDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieList.clear()
            movieList = ArrayList(movies)
        }
    }
}