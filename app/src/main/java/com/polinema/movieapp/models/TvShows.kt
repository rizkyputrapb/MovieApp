package com.polinema.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TvShows(
    var id: Int = 0,
    var in_production: Boolean = false,
    var first_air_date: String = "",
    var genres: @RawValue List<Genres>,
    var name: String = "",
    var poster_path: String = "",
    var vote_average: Double = 0.0,
    var overview: String = "",
    var created_by: @RawValue List<Creator>,
    var networks: @RawValue List<Networks>
    ): Parcelable
