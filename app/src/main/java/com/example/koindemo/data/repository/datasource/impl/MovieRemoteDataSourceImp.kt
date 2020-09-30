package com.example.koindemo.data.repository.datasource.impl

import com.example.koindemo.data.api.TMDBService
import com.example.koindemo.data.model.MovieList
import com.example.koindemo.data.repository.datasource.MovieRemoteDataSource
import kotlinx.coroutines.*
import retrofit2.Response

private const val TMDB_STARTING_PAGE_INDEX = 1

class MovieRemoteDataSourceImp(private val tmdbService: TMDBService, private val API_KEY: String) :
    MovieRemoteDataSource {

    override suspend fun getMoviesFromRemote(): Response<MovieList> = withContext(Dispatchers.IO){
        tmdbService.getPopularMovies(API_KEY)
    }

}