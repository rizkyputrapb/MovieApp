package com.polinema.movieapp.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubuserdetailed.api.Resource
import com.polinema.movieapp.models.TvShows
import com.polinema.movieapp.utils.api.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TvshowViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
    private val _navigatetoDetail = MutableLiveData<TvShows?>()

    fun getPopularTvshows() = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesRepository.getPopularTvShows()))
        } catch (e: Exception) {
            emit(
                Resource.error(
                    null,
                    e.message ?: "Unknown Error"
                )
            )
            Log.e("viewModel", "popularTvshows error: ${e.message}")
        }
    }

    fun getTvshowsDetails(tvshow_id: String) = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesRepository.getTvShowDetails(tvshow_id)))
        } catch (e: Exception) {
            emit(
                Resource.error(
                    null,
                    e.message ?: "Unknown Error"
                )
            )
            Log.e("viewModel", "TvshowsDetails error: ${e.message}")
        }
    }

    fun navigatetoDetail(): LiveData<TvShows?> {
        return _navigatetoDetail
    }

    fun onTvshowClicked(tvshow: TvShows) {
        _navigatetoDetail.value = tvshow
    }

    fun onTvshowDetailNavigated() {
        _navigatetoDetail.value = null
    }
}