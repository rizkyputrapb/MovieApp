package com.polinema.movieapp.tvshow

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
import com.polinema.movieapp.databinding.TvshowFragmentBinding
import com.polinema.movieapp.home.HomeFragmentDirections
import com.polinema.movieapp.models.TvShows
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvshowFragment : Fragment() {

    companion object {
        fun newInstance() = TvshowFragment()
    }

    private val viewModel: TvshowViewModel by viewModels()
    lateinit var binding: TvshowFragmentBinding
    private lateinit var tvShowAdapter: TvshowAdapter

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
        viewModel.getPopularTvshows().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> binding.progressBar2.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    val tvShowList = ArrayList<TvShows>()
                    it.data?.let { envelope ->
                        for (items in envelope.results){
                            viewModel.getTvshowsDetails(items.id.toString()).observe(viewLifecycleOwner, { resources ->
                                when(resources.status) {
                                    Status.SUCCESS -> {
                                        resources.data?.let { tvshows ->
                                            Log.d("tvShowFrag", "tvShow title: ${tvshows.name}")
                                            Log.d("tvShowFrag", "director : ${tvshows.created_by.map { it.name }}")
                                            Log.d("tvShowFrag", "genres : ${tvshows.genres.map { it.name }}")
                                            tvShowList.add(tvshows)
                                        }
                                    }
                                }
                                tvShowAdapter.tvShowList = tvShowList
                                tvShowAdapter.notifyDataSetChanged()
                            })
                        }
                    }
                    binding.progressBar2.visibility = View.INVISIBLE
                    binding.rvTvShow.visibility = View.VISIBLE
                }
                Status.ERROR -> Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.navigatetoDetail().observe(viewLifecycleOwner, { tvshows ->
            if (tvshows != null) {
                val action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(tvshows = tvshows, movies = null)
                Navigation.findNavController(requireView()).navigate(action)
                viewModel.onTvshowDetailNavigated()
            }

        })
    }

    fun setupRvMovies() {
        val recyclerView = binding.rvTvShow
        tvShowAdapter = TvshowAdapter(object : TvshowClickListener {
            override fun onTvshowClicked(tvShows: TvShows) {
                viewModel.onTvshowClicked(tvShows)
            }

        })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

}