package com.polinema.movieapp.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.models.TvShows
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    var movies = MutableLiveData<Movies>()
    var tvshows = MutableLiveData<TvShows>()

    fun setMoviesData(dataMovies: Movies?) {
        movies.postValue(dataMovies!!)
        Log.d("movieDetails", "Genre: ${movies.value?.genres?.map { it.name }?.toTypedArray()}")
    }

    fun setTvshowsData(dataTvShows: TvShows?){
        tvshows.postValue(dataTvShows!!)
        Log.d("tvshowDetails", "Genre: ${tvshows.value?.genres?.map { it.name }?.toTypedArray()}")
    }
}