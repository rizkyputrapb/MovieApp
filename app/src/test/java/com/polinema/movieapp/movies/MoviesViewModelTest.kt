package com.polinema.movieapp.movies

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polinema.movieapp.utils.ResourceHelper
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock


@RunWith(AndroidJUnit4::class)
class MoviesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: MoviesViewModel

    @Mock
    lateinit var mockApplicationContext: Context

    @Before
    fun setUp() {
        mockApplicationContext = ApplicationProvider.getApplicationContext()
        viewModel = MoviesViewModel(ResourceHelper(mockApplicationContext))
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