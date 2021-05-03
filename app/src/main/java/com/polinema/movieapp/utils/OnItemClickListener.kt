package com.polinema.movieapp.utils

import com.polinema.movieapp.models.Movies

interface OnItemClickListener {
    fun onMovieClicked(movies: Movies?)
}