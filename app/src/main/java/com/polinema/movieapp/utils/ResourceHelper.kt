package com.polinema.movieapp.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.polinema.movieapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceHelper @Inject constructor(@ApplicationContext applicationContext: Context) {
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

    val dataTvshowReleaseYear = applicationContext.resources.getStringArray(R.array.tvshowreleaseyear)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, drawable: Int?) {
    drawable?.let {
        Glide.with(view.context)
            .load(drawable)
            .into(view)
    }
}