package com.polinema.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    var title: String = "",
    var genre: String = "",
    var rating: String = "",
    var duration: String = "",
    var overview: String = "",
    var releaseYear: String = "2018",
    var poster: Int = 0
) : Parcelable
