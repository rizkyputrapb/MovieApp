package com.polinema.movieapp.utils

import com.polinema.movieapp.models.Movies
import javax.inject.Inject


class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MoviesDataSource {
    override fun getMoviesData(): List<Movies> {
        val movieTitles = remoteDataSource.getMovieTitles()
        val movieRatings = remoteDataSource.getMovieRatings()
        val movieGenres = remoteDataSource.getMovieGenres()
        val movieDurations = remoteDataSource.getMovieDurations()
        val moviePosters = remoteDataSource.getMoviePosters()
        val movieOverviews = remoteDataSource.getMovieOverviews()
        val moviesList = ArrayList<Movies>()
        for (position in movieTitles.indices) {
            val movies = Movies(
                title = movieTitles[position],
                poster = moviePosters.getResourceId(position, -1),
                duration = movieDurations[position],
                overview = movieOverviews[position],
                rating = movieRatings[position],
                genre = movieGenres[position]
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    override fun getTvShowData(): List<Movies> {
        val tvShowTitles = remoteDataSource.getTvShowTitles()
        val tvShowRatings = remoteDataSource.getTvShowRatings()
        val tvShowGenres = remoteDataSource.getTvShowGenres()
        val tvShowDurations = remoteDataSource.getTvShowDurations()
        val tvShowPosters = remoteDataSource.getTvShowPosters()
        val tvShowOverviews = remoteDataSource.getTvShowOverviews()
        val tvShowReleaseYear = remoteDataSource.getTvShowReleaseYear()
        val tvShowList = ArrayList<Movies>()
        for (position in tvShowTitles.indices) {
            val tvshow = Movies(
                title = tvShowTitles[position],
                poster = tvShowPosters.getResourceId(position, -1),
                duration = tvShowDurations[position],
                overview = tvShowOverviews[position],
                rating = tvShowRatings[position],
                genre = tvShowGenres[position],
                releaseYear = tvShowReleaseYear[position]
            )
            tvShowList.add(tvshow)
        }
        return tvShowList
    }


}