package com.example.koindemo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.koindemo.R
import com.example.koindemo.data.api.TMDBService
import com.example.koindemo.data.repository.MovieRepository
import com.example.koindemo.data.repository.datasource.MovieCacheDataSource
import com.example.koindemo.data.repository.datasource.MovieLocalDataSource
import com.example.koindemo.data.repository.datasource.MovieRemoteDataSource
import com.example.koindemo.data.repository.datasource.impl.RetrofitInstances
import com.example.koindemo.domain.MovieUseCase
import com.example.koindemo.presentation.viewmodel.movie.MovieViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val movieViewModel: MovieViewModel = get()
        movieViewModel.getMovies().observe(this, Observer {
            Log.i("data_result", it.toString())
        })
    }
}