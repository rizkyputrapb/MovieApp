package com.polinema.movieapp.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    var movies = MutableLiveData<Movies>()

    fun setMoviesData(dataMovies: Movies?) {
        movies.postValue(dataMovies!!)
    }
}