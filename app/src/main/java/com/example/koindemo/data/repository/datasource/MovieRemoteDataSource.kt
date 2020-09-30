package com.example.koindemo.data.repository.datasource

import com.example.koindemo.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMoviesFromRemote(): Response<MovieList>
}