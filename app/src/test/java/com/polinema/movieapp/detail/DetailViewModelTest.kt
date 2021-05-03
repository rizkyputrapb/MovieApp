package com.polinema.movieapp.detail

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailViewModelTest: TestCase() {
    private lateinit var viewModel: DetailViewModel

    @Before
    public override fun setUp() {
        viewModel = DetailViewModel()
        super.setUp()
    }

    public override fun tearDown() {}

    @Test
    fun testGetMovies() {
        val moviesEntities = viewModel.movies
        assertNotNull(moviesEntities)
        assertEquals(null, moviesEntities.value)
    }
}