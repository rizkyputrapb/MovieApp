package com.polinema.movieapp.utils

import com.polinema.movieapp.models.Movies

interface MoviesDataSource {
    fun getMoviesData(): List<Movies>
    fun getTvShowData(): List<Movies>
}