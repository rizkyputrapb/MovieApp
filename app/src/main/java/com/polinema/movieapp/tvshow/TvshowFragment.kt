package com.polinema.movieapp.tvshow

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
import com.polinema.movieapp.databinding.TvshowFragmentBinding
import com.polinema.movieapp.home.HomeFragmentDirections
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.OnItemClickListener
import com.polinema.movieapp.utils.RVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvshowFragment : Fragment() {

    companion object {
        fun newInstance() = TvshowFragment()
    }

    private val viewModel: TvshowViewModel by viewModels()
    lateinit var binding: TvshowFragmentBinding
    private lateinit var RVAdapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.tvshow_fragment, container, false)
        vmSetup()
        setupRvMovies()
        return binding.root
    }

    fun vmSetup() {
        viewModel.tvshowLiveData.observe(viewLifecycleOwner, {
            RVAdapter.moviesList = it
            RVAdapter.notifyDataSetChanged()
        })
        viewModel.navigatetoDetail().observe(viewLifecycleOwner, { tvshows ->
            if (tvshows != null) {
                val action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(tvshows)
                Navigation.findNavController(requireView()).navigate(action)
                viewModel.onTvshowDetailNavigated()
            }

        })
    }

    fun setupRvMovies() {
        val recyclerView = binding.rvTvShow
        RVAdapter = RVAdapter(object : OnItemClickListener {
            override fun onMovieClicked(movies: Movies?) {
                viewModel.onTvshowClicked(movies)
            }
        })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = RVAdapter
        }
    }

}