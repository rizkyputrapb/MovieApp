package com.polinema.movieapp.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.ResourceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvshowViewModel @Inject constructor(private val dataHelper: ResourceHelper): ViewModel() {
    var tvshowLiveData = MutableLiveData<ArrayList<Movies>>()
    var tvshowList: ArrayList<Movies> = ArrayList()
    private val _navigatetoDetail = MutableLiveData<Movies?>()

    init {
        tvshowLiveData.value = tvshowList
        populateList()
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

    fun populateList() {
        for (position in dataHelper.dataTVshowtitles.indices) {
            val movies = Movies(
                title = dataHelper.dataTVshowtitles[position],
                poster = dataHelper.dataTvshowPosters.getResourceId(position, -1),
                duration = dataHelper.dataTvshowDuration[position],
                overview = dataHelper.dataTvshowOverview[position],
                rating = dataHelper.dataTvshowRating[position],
                genre = dataHelper.dataTvshowGenre[position],
                releaseYear = dataHelper.dataTvshowReleaseYear[position]
            )
            tvshowList.add(movies)
        }
    }
}