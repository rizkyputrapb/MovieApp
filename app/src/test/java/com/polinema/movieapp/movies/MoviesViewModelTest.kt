package com.polinema.movieapp.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.polinema.movieapp.utils.MoviesRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: MoviesViewModel

    @Mock
    lateinit var  moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun testGetMoviesLiveData() {
        viewModel.moviesLiveData.postValue(viewModel.moviesList)
        viewModel.moviesLiveData.observeForever {}
        assertEquals(viewModel.moviesLiveData.value, viewModel.moviesList)
    }

    @Test
    fun testGetMoviesList() {
        val moviesEntities = viewModel.moviesList
        assertNotNull(moviesEntities)
        assertEquals(19, moviesEntities.size)
    }
}