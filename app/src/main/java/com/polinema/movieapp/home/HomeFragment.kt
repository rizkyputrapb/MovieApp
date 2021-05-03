package com.polinema.movieapp.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.polinema.movieapp.R
import com.polinema.movieapp.databinding.HomeFragmentBinding
import com.polinema.movieapp.movies.MoviesFragment
import com.polinema.movieapp.tvshow.TvshowFragment

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
    private val titles = arrayOf("Movies", "TV Shows")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        tabLayout()
        return binding.root
    }

    private fun tabLayout() {
        val fragmentList = arrayListOf<Fragment>(
            MoviesFragment(),
            TvshowFragment()
        )
        val tabLayout = binding.tabLayout
        viewPagerAdapter = ViewPagerAdapter(fragmentList, lifecycle, activity?.supportFragmentManager!!)
        binding.tabPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, binding.tabPager) { tab, position ->
            tab.text = titles[position]
            binding.tabPager.setCurrentItem(tab.position, true)
        }.attach()
    }

}