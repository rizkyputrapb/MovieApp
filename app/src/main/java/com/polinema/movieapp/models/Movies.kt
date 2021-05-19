package com.polinema.movieapp.models

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.polinema.movieapp.utils.api.Constants.IMAGE_URL
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Movies(
    var id: Int = 0,
    var title: String = "",
    var genres: @RawValue List<Genres>,
    var vote_average: Double = 0.0,
    var runtime: Int = 0,
    var overview: String = "",
    var release_date: String = "",
    var poster_path: String = ""
) : Parcelable {
    var genreNames: Array<String?> = genres.map { it.name }.toTypedArray()
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(IMAGE_URL + url).into(view)
    }
}
