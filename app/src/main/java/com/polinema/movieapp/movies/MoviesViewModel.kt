package com.polinema.movieapp.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.githubuserdetailed.api.Resource
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.api.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
    private val _navigatetoDetail = MutableLiveData<Movies?>()

    fun getPopularMovies() = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesRepository.getPopularMovies()))
        } catch (e: Exception) {
            emit(
                Resource.error(
                    null,
                    e.message ?: "Unknown Error"
                )
            )
            Log.e("viewModel", "popularMovies error: ${e.message}")
        }
    }

    fun getMovieDetails(movie_id: String) = liveData(Dispatchers.Default) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(moviesRepository.getMovieDetails(movie_id)))
        } catch (e: Exception) {
            emit(
                Resource.error(
                    null,
                    e.message ?: "Unknown Error"
                )
            )
            Log.e("viewModel", "movieDetails error: ${e.message}")
        }
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