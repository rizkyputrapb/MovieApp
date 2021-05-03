package com.polinema.movieapp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.polinema.movieapp.R
import com.polinema.movieapp.databinding.MoviesFragmentBinding
import com.polinema.movieapp.home.HomeFragmentDirections
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.OnItemClickListener
import com.polinema.movieapp.utils.RVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private val moviesViewModel: MoviesViewModel by viewModels()
    lateinit var binding: MoviesFragmentBinding
    private lateinit var RvAdapter: RVAdapter

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
        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner, {
            RvAdapter.moviesList = it
            RvAdapter.notifyDataSetChanged()
        })
        moviesViewModel.navigatetoDetail().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movies)
                Navigation.findNavController(requireView()).navigate(action)
                moviesViewModel.onMovieDetailNavigated()
            }
        })
    }

    fun setupRvMovies() {
        val recyclerView = binding.rvMovies
        RvAdapter = RVAdapter(object : OnItemClickListener {
            override fun onMovieClicked(movies: Movies?) {
                moviesViewModel.onMovieClicked(movies)
            }
        })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = RvAdapter
        }
    }

}