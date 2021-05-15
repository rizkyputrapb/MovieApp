package com.polinema.movieapp

import android.content.Context
import com.polinema.movieapp.models.Movies
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceHelperTest @Inject constructor(@ApplicationContext applicationContext: Context) {

    val dataMovieTitles = applicationContext.resources.getStringArray(R.array.movietitles)

    val dataMoviePosters = applicationContext.resources.obtainTypedArray(R.array.movieposter)

    val dataMovieDuration = applicationContext.resources.getStringArray(R.array.movieduration)

    val dataMovieGenre = applicationContext.resources.getStringArray(R.array.moviegenre)

    val dataMovieRating = applicationContext.resources.getStringArray(R.array.movierating)

    val dataMovieOverview = applicationContext.resources.getStringArray(R.array.movieoverview)

    val dataTVshowtitles = applicationContext.resources.getStringArray(R.array.tvshowtitle)

    val dataTvshowPosters = applicationContext.resources.obtainTypedArray(R.array.tvshowposter)

    val dataTvshowDuration = applicationContext.resources.getStringArray(R.array.tvshowduration)

    val dataTvshowGenre = applicationContext.resources.getStringArray(R.array.tvshowgenre)

    val dataTvshowRating = applicationContext.resources.getStringArray(R.array.tvshowrating)

    val dataTvshowOverview = applicationContext.resources.getStringArray(R.array.tvshowoverview)

    val dataTvshowReleaseYear =
        applicationContext.resources.getStringArray(R.array.tvshowreleaseyear)

    fun populateMovieList(): ArrayList<Movies> {
        var moviesList: ArrayList<Movies> = ArrayList()
        for (position in dataMovieTitles.indices) {
            val movies = Movies(
                title = dataMovieTitles[position],
                poster = dataMoviePosters.getResourceId(position, -1),
                duration = dataMovieDuration[position],
                overview = dataMovieOverview[position],
                rating = dataMovieRating[position],
                genre = dataMovieGenre[position]
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun populateTvShowList(): ArrayList<Movies> {
        var TvShowList: ArrayList<Movies> = ArrayList()
        for (position in dataTVshowtitles.indices) {
            val tvshows = Movies(
                title = dataTVshowtitles[position],
                poster = dataTvshowPosters.getResourceId(position, -1),
                duration = dataTvshowDuration[position],
                overview = dataTvshowOverview[position],
                rating = dataTvshowRating[position],
                genre = dataTvshowGenre[position]
            )
            TvShowList.add(tvshows)
        }
        return TvShowList
    }
}