package com.polinema.movieapp.utils.api

import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.models.TvShows
import com.polinema.movieapp.utils.api.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/3/movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovies(): Envelope<List<Movies>>

    @GET("/3/movie/{movie_id}?api_key=$API_KEY&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: String?): Movies

    @GET("3/tv/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularTvShows(): Envelope<List<TvShows>>

    @GET("/3/tv/{tvshow_id}?api_key=$API_KEY&language=en-US")
    suspend fun getTvShowDetails(@Path("tvshow_id") tvshow_id: String?): TvShows
}