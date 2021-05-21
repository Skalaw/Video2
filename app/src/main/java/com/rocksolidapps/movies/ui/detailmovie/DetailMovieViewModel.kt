package com.rocksolidapps.movies.ui.detailmovie

import androidx.lifecycle.viewModelScope
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.MovieDetailsUi
import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.usecase.FetchMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchMovieUseCase
import com.rocksolidapps.movies.R
import com.rocksolidapps.movies.data.repository.SchedulersInjector
import com.rocksolidapps.movies.ui.BaseViewModel
import com.rocksolidapps.movies.ui.DataState
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val schedulers: SchedulersInjector,
    private val configRepository: ConfigRepository,
    private val fetchMovieRxUseCase: FetchMovieRxUseCase,
    private val fetchMovieUseCase: FetchMovieUseCase,
) : BaseViewModel() {
    private val _movieDetailStateFlow = MutableStateFlow<DataState<MovieDetailsUi>>(DataState.LoadingState)
    val movieDetailStateFlow: StateFlow<DataState<MovieDetailsUi>> = _movieDetailStateFlow

    private var movieId: Int = 0

    fun initViewModel(movieId: Int) {
        this.movieId = movieId
        fetchDetailMovie()
    }

    fun fetchDetailMovie() {
        if (configRepository.useRxJava) {
            fetchDetailMovieRx()
        } else {
            fetchDetailMovieCoroutine()
        }
    }

    private fun fetchDetailMovieRx() {
        viewModelScope.launch {
            _movieDetailStateFlow.value = DataState.LoadingState
            fetchMovieRxUseCase(movieId)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
                .subscribeBy(
                    onNext = { _movieDetailStateFlow.value = DataState.Data(it) },
                    onError = { _movieDetailStateFlow.value = DataState.ErrorState(errorMessage = it.message) }
                )
        }
    }

    private fun fetchDetailMovieCoroutine() {
        viewModelScope.launch {
            _movieDetailStateFlow.value = DataState.LoadingState
            when (val result = fetchMovieUseCase(movieId)) {
                is ResultWrapper.Success -> _movieDetailStateFlow.value = DataState.Data(result.value)
                is ResultWrapper.GenericError -> _movieDetailStateFlow.value = DataState.ErrorState(errorMessage = result.error)
                is ResultWrapper.NetworkError -> _movieDetailStateFlow.value = DataState.ErrorState(resString = R.string.errorNotInternetConnection)
            }
        }
    }
}