package com.polinema.movieapp.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polinema.movieapp.databinding.ItemMovieBinding
import com.polinema.movieapp.models.Movies

class MoviesAdapter(private var onItemMovieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var moviesList: List<Movies>? = null
        set(moviesList) {
            notifyDataSetChanged()
            field = moviesList
        }

    class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movies?, movieClickListener: MovieClickListener) {
            binding.movies = movies
            Log.d("movieAdapter", "runtime: ${movies?.runtime}")
            Log.d("movieAdapter", "poster_path: ${movies?.poster_path}")
            val genres = movies?.genres?.map { it.name }.toString()
            binding.movieGenre.text = genres.substring(1, genres.length - 1)
            binding.onclick = movieClickListener
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
//        Log.d("listSize", "Size: ${moviesList?.size}")
        return moviesList?.size ?: 0
    }
}