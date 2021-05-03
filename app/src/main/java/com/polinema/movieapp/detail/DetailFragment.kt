package com.polinema.movieapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.polinema.movieapp.R
import com.polinema.movieapp.databinding.DetailFragmentBinding
import com.polinema.movieapp.models.Movies

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()
    lateinit var binding: DetailFragmentBinding
    var movies: Movies? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        vmSetup()
        return binding.root
    }

    private fun vmSetup() {
        binding.viewmodel = viewModel
        movies = arguments?.let { DetailFragmentArgs.fromBundle(it).movies }
        viewModel.setMoviesData(movies)
    }

}