package com.rocksolidapps.movies.ui.detailmovie

import androidx.lifecycle.viewModelScope
import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.usecase.FetchMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchMovieUseCase
import com.rocksolidapps.movies.data.repository.SchedulersInjector
import com.rocksolidapps.movies.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val schedulers: SchedulersInjector,
    private val configRepository: ConfigRepository,
    private val fetchMovieRxUseCase: FetchMovieRxUseCase,
    private val fetchMovieUseCase: FetchMovieUseCase,
) : BaseViewModel() {
    init {
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
            // todo
        }
    }

    private fun fetchDetailMovieCoroutine() {
        viewModelScope.launch {
            // todo
        }
    }
}