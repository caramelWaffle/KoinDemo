package com.example.koindemo.presentation.viewmodel.movie

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import com.example.koindemo.domain.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    val title = MutableLiveData<String>()

    init {
        this.title.value = "Popular Movies"
    }

    fun getMovies() = liveData {
        emit(movieUseCase.getMovies())
    }

    fun updateMovies() = liveData {
        emit(movieUseCase.updateMovies())
    }


}