package com.polinema.movieapp.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polinema.movieapp.databinding.ItemMovieBinding
import com.polinema.movieapp.models.Movies

class RVAdapter(onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RVAdapter.MoviesViewHolder>() {
    var moviesList: List<Movies>? = null
        set(moviesList) {
            notifyDataSetChanged()
            field = moviesList
        }

    private var onItemMovieClickListener = onItemClickListener

    class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies?, onItemClickListener: OnItemClickListener) {
            binding.movies = movies
            Glide.with(itemView).load(movies?.poster).into(binding.moviePoster)
            binding.onclick = onItemClickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = moviesList?.get(position)
        Log.d("movieData", "Adding: ${movies?.title}")
        holder.bind(movies, onItemMovieClickListener)
    }

    override fun getItemCount(): Int {
        Log.d("listSize", "Size: ${moviesList?.size}")
        return moviesList?.size ?: 0
    }
}