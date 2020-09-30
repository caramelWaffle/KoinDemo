package com.example.koindemo

import android.app.Application
import com.example.koindemo.data.api.TMDBService
import com.example.koindemo.data.db.MovieDAO
import com.example.koindemo.data.db.TMDBDatabase
import com.example.koindemo.data.repository.MovieRepository
import com.example.koindemo.data.repository.MovieRepositoryImp
import com.example.koindemo.data.repository.datasource.MovieCacheDataSource
import com.example.koindemo.data.repository.datasource.MovieLocalDataSource
import com.example.koindemo.data.repository.datasource.MovieRemoteDataSource
import com.example.koindemo.data.repository.datasource.impl.MovieCacheDataSourceImpl
import com.example.koindemo.data.repository.datasource.impl.MovieLocalDataSourceImp
import com.example.koindemo.data.repository.datasource.impl.MovieRemoteDataSourceImp
import com.example.koindemo.data.repository.datasource.impl.RetrofitInstances
import com.example.koindemo.domain.MovieUseCase
import com.example.koindemo.presentation.di.tmdbModule
import com.example.koindemo.presentation.viewmodel.movie.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(tmdbModule)
            androidLogger()
        }
    }
}