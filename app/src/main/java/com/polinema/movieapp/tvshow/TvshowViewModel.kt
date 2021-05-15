package com.polinema.movieapp.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvshowViewModel @Inject constructor(moviesRepository: MoviesRepository): ViewModel() {
    var tvshowLiveData = MutableLiveData<ArrayList<Movies>>()
    var tvshowList: ArrayList<Movies> = ArrayList()
    private val _navigatetoDetail = MutableLiveData<Movies?>()

    init {
        tvshowLiveData.postValue(moviesRepository.getTvShowData() as ArrayList<Movies>)
    }

    fun navigatetoDetail(): LiveData<Movies?> {
        return _navigatetoDetail
    }

    fun onTvshowClicked(movies: Movies?) {
        _navigatetoDetail.value = movies
    }

    fun onTvshowDetailNavigated() {
        _navigatetoDetail.value = null
    }
}