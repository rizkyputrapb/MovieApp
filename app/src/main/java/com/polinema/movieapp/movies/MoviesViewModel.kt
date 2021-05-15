package com.polinema.movieapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {
    var moviesLiveData = MutableLiveData<ArrayList<Movies>>()
    var moviesList: ArrayList<Movies> = ArrayList()
    private val _navigatetoDetail = MutableLiveData<Movies?>()

    init {
        moviesLiveData.postValue(moviesRepository.getMoviesData() as ArrayList<Movies>)
    }

    fun navigatetoDetail(): LiveData<Movies?> {
        return _navigatetoDetail
    }

    fun onMovieClicked(movies: Movies?) {
        _navigatetoDetail.value = movies
    }

    fun onMovieDetailNavigated() {
        _navigatetoDetail.value = null
    }
}