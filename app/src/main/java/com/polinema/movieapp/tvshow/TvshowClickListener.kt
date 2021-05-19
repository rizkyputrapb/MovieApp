package com.polinema.movieapp.tvshow

import com.polinema.movieapp.models.TvShows

interface TvshowClickListener {
    fun onTvshowClicked(tvShows: TvShows)
}