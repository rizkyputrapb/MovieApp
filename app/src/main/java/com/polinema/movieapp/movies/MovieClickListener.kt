package com.polinema.movieapp.movies

import com.polinema.movieapp.models.Movies

interface MovieClickListener {
    fun onMovieClicked(movie: Movies?)
}