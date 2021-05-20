package com.rocksolidapps.movies.ui.discovermovie

import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.DiscoverMoviePageUi
import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieUseCase
import com.rocksolidapps.movies.TestSchedulerProvider
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
class DiscoverMovieViewModelTest {
    private val configRepository = mock<ConfigRepository>()
    private val fetchDiscoverMovieRxUseCase = mock<FetchDiscoverMovieRxUseCase>()
    private val fetchDiscoverMovieUseCase = mock<FetchDiscoverMovieUseCase>()

    private lateinit var viewModel: DiscoverMovieViewModel

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = DiscoverMovieViewModel(TestSchedulerProvider(), configRepository, fetchDiscoverMovieRxUseCase, fetchDiscoverMovieUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Reset page after refresh and fetch data -coroutines`() = runBlockingTest {
        // given
        whenever(configRepository.useRxJava).thenReturn(false)
        whenever(fetchDiscoverMovieUseCase.invoke(1)).thenReturn(createResultWrapperEmptyDiscoverMoviePager(1))
        viewModel.actualPage = 4

        // when
        viewModel.refreshDiscoverMovie()

        // then
        Assert.assertEquals(2, viewModel.actualPage)
    }

    @Test
    fun `Reset page after refresh and fetch data -RX`() = runBlockingTest {
        // given
        whenever(configRepository.useRxJava).thenReturn(true)
        whenever(fetchDiscoverMovieRxUseCase.invoke(1)).thenReturn(createObservableEmptyDiscoverMoviePager(1))
        viewModel.actualPage = 4

        // when
        viewModel.refreshDiscoverMovie()

        // then
        Assert.assertEquals(2, viewModel.actualPage)
    }

    @Test
    fun `Don't fetch when you are in last page -coroutines`() = runBlockingTest {
        // given
        whenever(configRepository.useRxJava).thenReturn(false)
        whenever(fetchDiscoverMovieUseCase.invoke(1)).thenReturn(createResultWrapperEmptyDiscoverMoviePager(1))
        whenever(fetchDiscoverMovieUseCase.invoke(2)).thenReturn(createResultWrapperEmptyDiscoverMoviePager(2))
        whenever(fetchDiscoverMovieUseCase.invoke(3)).thenReturn(createResultWrapperEmptyDiscoverMoviePager(3))
        whenever(fetchDiscoverMovieUseCase.invoke(4)).thenReturn(createResultWrapperEmptyDiscoverMoviePager(4))

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(2, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(3, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(4, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(true, viewModel.isLastPage.value)
        Assert.assertEquals(5, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(true, viewModel.isLastPage.value)
        Assert.assertEquals(5, viewModel.actualPage)
    }

    @Test
    fun `Don't fetch when you are in last page -RX`() {
        // given
        whenever(configRepository.useRxJava).thenReturn(true)
        whenever(fetchDiscoverMovieRxUseCase.invoke(1)).thenReturn(createObservableEmptyDiscoverMoviePager(1))
        whenever(fetchDiscoverMovieRxUseCase.invoke(2)).thenReturn(createObservableEmptyDiscoverMoviePager(2))
        whenever(fetchDiscoverMovieRxUseCase.invoke(3)).thenReturn(createObservableEmptyDiscoverMoviePager(3))
        whenever(fetchDiscoverMovieRxUseCase.invoke(4)).thenReturn(createObservableEmptyDiscoverMoviePager(4))

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(2, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(3, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(false, viewModel.isLastPage.value)
        Assert.assertEquals(4, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(true, viewModel.isLastPage.value)
        Assert.assertEquals(5, viewModel.actualPage)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(true, viewModel.isLastPage.value)
        Assert.assertEquals(5, viewModel.actualPage)
    }

    @Test
    fun `Number page don't changed when throw exception during fetch data -coroutines`() = runBlockingTest {
        // given
        whenever(configRepository.useRxJava).thenReturn(false)
        whenever(fetchDiscoverMovieUseCase.invoke(1)).thenReturn(ResultWrapper.NetworkError)

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(1, viewModel.actualPage)
    }

    @Test
    fun `Number page don't changed when throw exception during fetch data -RX`() {
        // given
        whenever(configRepository.useRxJava).thenReturn(true)
        whenever(fetchDiscoverMovieRxUseCase.invoke(1)).thenReturn(Observable.error(Throwable()))

        // when
        viewModel.fetchDiscoverMovie()

        // then
        Assert.assertEquals(1, viewModel.actualPage)
    }

    private fun createObservableEmptyDiscoverMoviePager(page: Int) = Observable.just(createEmptyDiscoverMoviePager(page))
    private fun createResultWrapperEmptyDiscoverMoviePager(page: Int) = ResultWrapper.Success(createEmptyDiscoverMoviePager(page))

    private fun createEmptyDiscoverMoviePager(page: Int) = DiscoverMoviePageUi(
        page = page,
        items = arrayListOf(),
        totalPages = 4
    )
}