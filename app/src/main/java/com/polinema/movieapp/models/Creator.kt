package com.polinema.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Creator(
    var name: String = "",
) : Parcelable
