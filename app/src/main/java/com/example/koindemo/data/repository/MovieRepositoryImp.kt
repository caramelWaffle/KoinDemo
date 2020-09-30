package com.example.koindemo.data.repository

import android.util.Log
import com.example.koindemo.data.model.Movie
import com.example.koindemo.data.repository.datasource.MovieCacheDataSource
import com.example.koindemo.data.repository.datasource.MovieLocalDataSource
import com.example.koindemo.data.repository.datasource.MovieRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImp(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {



    override suspend fun getMovies(): List<Movie>? {
        return getMovieFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newMovieList = getMovieFromAPI()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newMovieList)
        movieCacheDataSource.saveMoviesToCache(newMovieList)
        return newMovieList
    }


    suspend fun getMovieFromAPI(): List<Movie> {
        val movieList = ArrayList<Movie>()
        try {
            val response = movieRemoteDataSource.getMoviesFromRemote()
            val body = response.body()
            movieList.addAll(body!!.movies)
        } catch (e: Exception) {
            Log.i("myTag", e.message.toString())
        }
        return movieList
    }

    suspend fun getMovieFromDB(): List<Movie> {
        val movieList = ArrayList<Movie>()
        try {
            movieList.addAll(movieLocalDataSource.getMoviesFromDB())
        } catch (e: Exception) {
            Log.i("myTag", e.message.toString())
        }

        if (movieList.size == 0) {
            movieList.addAll(getMovieFromAPI())
            movieLocalDataSource.saveMoviesToDB(movieList)
        }
        return movieList

    }

    suspend fun getMovieFromCache(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val movieList = ArrayList<Movie>()

            try {
                movieList.addAll(movieCacheDataSource.getMoviesFromCache())
            } catch (e: Exception) {
                Log.i("myTag", e.message.toString())
            }

            if (movieList.size == 0) {
                movieList.addAll(getMovieFromDB())
                movieCacheDataSource.saveMoviesToCache(movieList)
            }

            movieList
        }
    }
}