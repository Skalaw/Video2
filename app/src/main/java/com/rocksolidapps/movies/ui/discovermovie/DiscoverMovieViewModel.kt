package com.rocksolidapps.movies.ui.discovermovie

import androidx.lifecycle.viewModelScope
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.DiscoverMoviePageUi
import com.rocksolidapps.core.domain.model.DiscoverMovieUi
import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieUseCase
import com.rocksolidapps.core.ext.plusAssign
import com.rocksolidapps.movies.data.repository.SchedulersInjector
import com.rocksolidapps.movies.tools.Consumable
import com.rocksolidapps.movies.ui.BaseViewModel
import com.rocksolidapps.movies.ui.UiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMovieViewModel @Inject constructor(
    private val schedulers: SchedulersInjector,
    private val configRepository: ConfigRepository,
    private val fetchDiscoverMovieRxUseCase: FetchDiscoverMovieRxUseCase,
    private val fetchDiscoverMovieUseCase: FetchDiscoverMovieUseCase
) : BaseViewModel() {
    private val _movieList = MutableStateFlow(UiModel(data = arrayListOf<DiscoverMovieUi>()))
    val movieList: StateFlow<UiModel<List<DiscoverMovieUi>>> = _movieList
    private val _isLastPage = MutableStateFlow(false)
    val isLastPage: StateFlow<Boolean> = _isLastPage

    var actualPage = 1

    init {
        fetchDiscoverMovie()
    }

    fun fetchDiscoverMovie() {
        viewModelScope.launch {
            if (_isLastPage.value) return@launch
            _movieList.value = _movieList.value.copy(isLoading = true)
            if (configRepository.useRxJava) {
                fetchDiscoverMovieRx()
            } else {
                fetchDiscoverMovieCoroutine()
            }
        }
    }

    fun refreshDiscoverMovie() {
        viewModelScope.launch {
            _movieList.value = _movieList.value.copy(data = arrayListOf(), isLoading = true)
            actualPage = 1
            _isLastPage.value = false
            fetchDiscoverMovie()
        }
    }

    private fun fetchDiscoverMovieRx(page: Int = actualPage) {
        viewModelScope.launch {
            disposables += fetchDiscoverMovieRxUseCase(page)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
                .subscribe({ discoverMoviePage ->
                    propagateData(discoverMoviePage)
                }, { throwable ->
                    _movieList.value = _movieList.value.copy(isLoading = false, error = Consumable(Unit))
                })
        }
    }

    private fun fetchDiscoverMovieCoroutine(page: Int = actualPage) {
        viewModelScope.launch {
            when (val result = fetchDiscoverMovieUseCase(page)) {
                is ResultWrapper.Success -> propagateData(result.value)
                is ResultWrapper.GenericError -> _movieList.value = _movieList.value.copy(isLoading = false, error = Consumable(Unit))
                is ResultWrapper.NetworkError -> _movieList.value = _movieList.value.copy(isLoading = false, error = Consumable(Unit))
            }
        }
    }

    private fun propagateData(discoverMoviePageUi: DiscoverMoviePageUi) {
        val oldList = _movieList.value.data
        val newList = discoverMoviePageUi.items
        _movieList.value = _movieList.value.copy(isLoading = false, data = arrayListOf<DiscoverMovieUi>().apply {
            addAll(oldList)
            addAll(newList)
        })
        _isLastPage.value = actualPage == discoverMoviePageUi.totalPages
        actualPage++
    }
}