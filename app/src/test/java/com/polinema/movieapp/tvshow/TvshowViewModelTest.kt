package com.polinema.movieapp.tvshow

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.polinema.movieapp.utils.ResourceHelper
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class TvshowViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: TvshowViewModel

    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        mockContext = ApplicationProvider.getApplicationContext()
        viewModel = TvshowViewModel(ResourceHelper(mockContext))
    }

    @Test
    fun testGetTvshowLiveData() {
        viewModel.tvshowLiveData.postValue(viewModel.tvshowList)
        viewModel.tvshowLiveData.observeForever{}
        Assert.assertEquals(viewModel.tvshowLiveData.value, viewModel.tvshowList)
    }

    @Test
    fun testGetTvshowList() {
        val tvshowEntities = viewModel.tvshowList
        Assert.assertNotNull(tvshowEntities)
        Assert.assertEquals(15, tvshowEntities.size)
    }
}