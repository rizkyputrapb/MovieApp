package com.polinema.movieapp.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polinema.movieapp.ResourceHelperTest
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class DetailViewModelTest: TestCase() {
    private lateinit var viewModel: DetailViewModel
    @Mock
    lateinit var mockApplicationContext: Context
    lateinit var  resourceHelperTest: ResourceHelperTest

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        mockApplicationContext = ApplicationProvider.getApplicationContext()
        viewModel = DetailViewModel()
        resourceHelperTest = ResourceHelperTest(mockApplicationContext)
    }

    @Test
    fun testSetMovies() {
        val dummy = resourceHelperTest.populateMovieList()[0]
        viewModel.setMoviesData(dummy)
        assertEquals(dummy, viewModel.movies.value)
    }
}