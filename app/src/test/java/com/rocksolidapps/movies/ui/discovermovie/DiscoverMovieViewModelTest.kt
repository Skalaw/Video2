package com.rocksolidapps.movies.ui.discovermovie

import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieUseCase
import com.rocksolidapps.movies.data.repository.SchedulersInjector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DiscoverMovieViewModelTest {
    private val schedulers = mock<SchedulersInjector>()
    private val configRepository = mock<ConfigRepository>()
    private val fetchDiscoverMovieRxUseCase = mock<FetchDiscoverMovieRxUseCase>()
    private val fetchDiscoverMovieUseCase = mock<FetchDiscoverMovieUseCase>()

    private lateinit var viewModel: DiscoverMovieViewModel

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = DiscoverMovieViewModel(schedulers, configRepository, fetchDiscoverMovieRxUseCase, fetchDiscoverMovieUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Reset page after refresh`() = runBlockingTest {
        // given
        viewModel.actualPage = 4

        // when
        viewModel.refreshDiscoverMovie()

        // then
        Assert.assertEquals(viewModel.actualPage, 1)
    }
}