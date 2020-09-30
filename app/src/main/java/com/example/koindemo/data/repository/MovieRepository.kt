package com.example.koindemo.data.repository

import com.example.koindemo.data.model.Movie

interface MovieRepository {
    // contact with DAO

    suspend fun getMovies(): List<Movie>?

    suspend fun updateMovies(): List<Movie>?
}