package com.example.koindemo.presentation.di

import com.example.koindemo.R
import com.example.koindemo.data.api.TMDBService
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
import com.example.koindemo.presentation.viewmodel.movie.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tmdbModule = module {
    single<MovieCacheDataSource> { MovieCacheDataSourceImpl() }
    single { TMDBDatabase.getInstance(androidContext()).movieDAO() }
    single<MovieLocalDataSource> { MovieLocalDataSourceImp(get()) }
    single { RetrofitInstances(androidContext().getString(R.string.base_url)).build() }
    single<MovieRemoteDataSource> {
        MovieRemoteDataSourceImp(
            get(),
            androidContext().getString(R.string.TMDB_API_KEY)
        )
    }
    single<MovieRepository> { MovieRepositoryImp(get(), get(), get()) }
    single { MovieUseCase(get()) }
    viewModel { MovieViewModel(get()) }
}