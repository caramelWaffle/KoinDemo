package com.example.koindemo.data.db

import androidx.room.*
import com.example.koindemo.data.model.Movie

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM ${Movie.TABLE_NAME}")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM ${Movie.TABLE_NAME}")
    suspend fun getMovies(): List<Movie>

}