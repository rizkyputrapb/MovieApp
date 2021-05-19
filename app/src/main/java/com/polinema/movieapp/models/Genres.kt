package com.polinema.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(var id: Int = 0, var name: String = ""): Parcelable