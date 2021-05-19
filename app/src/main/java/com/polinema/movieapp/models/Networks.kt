package com.polinema.movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Networks(
    var name: String = "",
    var logo_path: String = ""
):Parcelable