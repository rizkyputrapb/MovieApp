package com.polinema.movieapp.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubuserdetailed.api.Resource
import com.polinema.movieapp.CoroutineTestRules
import com.polinema.movieapp.models.Movies
import com.polinema.movieapp.utils.api.Envelope
import com.polinema.movieapp.utils.api.MoviesRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    @get:Rule
    var coroutinesTestRule = CoroutineTestRules()
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: MoviesViewModel

    @Mock
    lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<Resource<Envelope<List<Movies>>>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesRepository)
    }

    @Test
    fun testGetPopularMovies() = coroutinesTestRule.testDispatcher.runBlockingTest {
        var moviesList: List<Movies>? = 0
        viewModel.getPopularMovies().observe()
        assertEquals(20, moviesList?.size)
    }

//    @Test
//    fun testGetMoviesList() {
//        val moviesEntities = viewModel.moviesList
//        assertNotNull(moviesEntities)
//        assertEquals(19, moviesEntities.size)
//    }
}