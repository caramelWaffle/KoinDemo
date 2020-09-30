package com.example.koindemo.data.api

import com.example.koindemo.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        APIKey: String): Response<MovieList>
}