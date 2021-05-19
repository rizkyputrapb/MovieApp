package com.polinema.movieapp.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.polinema.movieapp.R
import com.polinema.movieapp.databinding.DetailFragmentBinding
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.models.TvShows

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    lateinit var binding: DetailFragmentBinding
    var movies: Movies? = null
    var tvShows: TvShows? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        vmSetup()
        if (movies != null) {
            val releaseYear = movies?.release_date?.substring(0, 4)
            val genres = movies?.genres?.map { it.name }.toString()
            val hours = Integer.toString(movies!!.runtime / 60)
            val minutes = Integer.toString(movies!!.runtime % 60)
            binding.txtReleaseGenre.text =
                releaseYear + " | " + genres.substring(1, genres.length - 1)
            binding.detailsContent.detailDuration.text = hours + "h" + minutes + "m"
        } else {
            val releaseYear = tvShows?.first_air_date?.substring(0, 4)
            val genres = tvShows?.genres?.map { it.name }.toString()
            val networks = tvShows?.networks?.map { it.name }.toString()
            Log.d("tvShowDetails", "networks: $networks")
            if (tvShows?.created_by?.size != 0) {
                val creator = tvShows?.created_by?.map { it.name }.toString()
                binding.txtReleaseGenre.text = creator.substring(
                    1, creator.length - 1
                ) + " | " + releaseYear + " | " +genres.substring(1, genres.length - 1)
            } else {
                binding.txtReleaseGenre.text =
                    releaseYear + " | " + genres.substring(1, genres.length - 1)
            }
            binding.detailsContent.detailDuration.text = networks.substring(1, networks.length - 1)
        }
        return binding.root
    }

    private fun vmSetup() {
        binding.viewmodel = viewModel
        movies = arguments?.let { DetailFragmentArgs.fromBundle(it).movies }
        tvShows = arguments?.let { DetailFragmentArgs.fromBundle(it).tvshows }
        if (movies != null) {
            viewModel.setMoviesData(movies)
        } else {
            viewModel.setTvshowsData(tvShows)
        }
    }

}