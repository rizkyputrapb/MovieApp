package com.polinema.movieapp.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.polinema.movieapp.movies.MoviesFragment
import com.polinema.movieapp.tvshow.TvshowFragment

class ViewPagerAdapter(list: ArrayList<Fragment>, lifecycle: Lifecycle, fragment: FragmentManager) :
    FragmentStateAdapter(fragment, lifecycle) {

    val fragmentList = list

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}