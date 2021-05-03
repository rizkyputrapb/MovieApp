package com.polinema.movieapp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.ResourceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val dataHelper: ResourceHelper) : ViewModel() {
    var moviesLiveData = MutableLiveData<ArrayList<Movies>>()
    var moviesList: ArrayList<Movies> = ArrayList()
    private val _navigatetoDetail = MutableLiveData<Movies?>()

    init {
        moviesLiveData.postValue(moviesList)
        populateList()
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

    fun populateList() {
        for (position in dataHelper.dataMovieTitles.indices) {
            val movies = Movies(
                title = dataHelper.dataMovieTitles[position],
                poster = dataHelper.dataMoviePosters.getResourceId(position, -1),
                duration = dataHelper.dataMovieDuration[position],
                overview = dataHelper.dataMovieOverview[position],
                rating = dataHelper.dataMovieRating[position],
                genre = dataHelper.dataMovieGenre[position]
            )
            moviesList.add(movies)
        }
    }
}