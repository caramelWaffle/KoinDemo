package com.example.koindemo.domain

import androidx.lifecycle.LiveData
import com.example.koindemo.data.model.Movie
import com.example.koindemo.data.repository.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {
    // execute function

    suspend fun getMovies(): List<Movie>? = movieRepository.getMovies()

    suspend fun updateMovies(): List<Movie>? = movieRepository.updateMovies()

}