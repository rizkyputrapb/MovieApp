package com.polinema.movieapp.tvshow

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polinema.movieapp.databinding.ItemTvshowBinding
import com.polinema.movieapp.models.TvShows

class TvshowAdapter(private var tvshowClickListener: TvshowClickListener) : RecyclerView.Adapter<TvshowAdapter.TvshowViewHolder>() {
    var tvShowList: List<TvShows>? = null
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    class TvshowViewHolder(var binding: ItemTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShows?, tvshowClickListener: TvshowClickListener) {
            binding.tvshow = tvShows
            binding.onclick = tvshowClickListener
            Log.d("tvshowAdapter", "in_production: ${tvShows?.in_production}")
            Log.d("tvshowAdapter", "poster_path: ${tvShows?.poster_path}")
            val genres = tvShows?.genres?.map { it.name }.toString()
            binding.tvshowGenre.text = genres.substring(1, genres.length - 1)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTvshowBinding.inflate(layoutInflater, parent, false)
        return TvshowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvshowViewHolder, position: Int) {
        val tvShow = tvShowList?.get(position)
        Log.d("tvShowData", "Adding: ${tvShow?.name}")
        holder.bind(tvShow, tvshowClickListener)
    }

    override fun getItemCount(): Int {
        return tvShowList?.size ?: 0
    }
}