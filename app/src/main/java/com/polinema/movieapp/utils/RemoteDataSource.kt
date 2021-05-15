package com.polinema.movieapp.utils

import javax.inject.Inject

class RemoteDataSource @Inject constructor(var resourceHelper: ResourceHelper){

    fun getMovieTitles() = resourceHelper.dataMovieTitles

    fun getMovieRatings() = resourceHelper.dataMovieRating

    fun getMovieDurations() = resourceHelper.dataMovieDuration

    fun getMovieGenres() = resourceHelper.dataMovieGenre

    fun getMoviePosters() = resourceHelper.dataMoviePosters

    fun getMovieOverviews() = resourceHelper.dataMovieOverview

    fun getTvShowTitles() = resourceHelper.dataTVshowtitles

    fun getTvShowRatings() = resourceHelper.dataTvshowRating

    fun getTvShowDurations() = resourceHelper.dataTvshowDuration

    fun getTvShowGenres() = resourceHelper.dataTvshowGenre

    fun getTvShowPosters() = resourceHelper.dataTvshowPosters

    fun getTvShowOverviews() = resourceHelper.dataTvshowOverview

    fun getTvShowReleaseYear() = resourceHelper.dataTvshowReleaseYear
}