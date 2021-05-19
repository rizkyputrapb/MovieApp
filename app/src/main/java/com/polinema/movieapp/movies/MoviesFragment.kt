package com.polinema.movieapp.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserdetailed.api.Status
import com.polinema.movieapp.R
import com.polinema.movieapp.databinding.MoviesFragmentBinding
import com.polinema.movieapp.home.HomeFragmentDirections
import com.polinema.movieapp.models.Movies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private val moviesViewModel: MoviesViewModel by viewModels()
    lateinit var binding: MoviesFragmentBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        setupRvMovies()
        vmSetup()
        return binding.root
    }

    fun vmSetup() {
        moviesViewModel.getPopularMovies().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    val movieList = ArrayList<Movies>()
                    it.data?.let { envelope ->
                        for (items in envelope.results){
                            moviesViewModel.getMovieDetails(items.id.toString()).observe(viewLifecycleOwner, { resources ->
                                when(resources.status) {
                                    Status.SUCCESS -> {
                                        resources.data?.let { movies ->
                                            Log.d("movieFrag", "movie title: ${movies.title}")
                                            Log.d("movieFrag", "genres : ${movies.genres.map { it.name }}")
                                            movieList.add(movies)
                                        }
                                    }
                                }
                                moviesAdapter.moviesList = movieList
                                moviesAdapter.notifyDataSetChanged()
                            })
                        }
                    }
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvMovies.visibility = View.VISIBLE
                }
                Status.ERROR -> Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        })
        moviesViewModel.navigatetoDetail().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                val action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(movies, null)
                Navigation.findNavController(requireView()).navigate(action)
                moviesViewModel.onMovieDetailNavigated()
            }
        })
    }

    fun setupRvMovies() {
        val recyclerView = binding.rvMovies
        moviesAdapter = MoviesAdapter(object : MovieClickListener {
            override fun onMovieClicked(movies: Movies?) {
                moviesViewModel.onMovieClicked(movies)
            }
        })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

}