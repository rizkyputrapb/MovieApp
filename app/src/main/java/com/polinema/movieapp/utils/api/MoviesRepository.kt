package com.polinema.movieapp.utils.api

import javax.inject.Inject

class MoviesRepository @Inject constructor(private var apiInterface: ApiInterface) {
    init {
        apiInterface = ApiBuilder.createService()
    }

    suspend fun getPopularMovies() = apiInterface.getPopularMovies()

    suspend fun getMovieDetails(movie_id: String) = apiInterface.getMovieDetails(movie_id)

    suspend fun getPopularTvShows() = apiInterface.getPopularTvShows()

    suspend fun getTvShowDetails(tvshow_id: String) = apiInterface.getTvShowDetails(tvshow_id)
}