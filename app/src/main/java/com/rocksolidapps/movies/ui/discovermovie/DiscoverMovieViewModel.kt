package com.rocksolidapps.movies.ui.discovermovie

import androidx.lifecycle.viewModelScope
import com.rocksolidapps.core.domain.model.MovieSimple
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCase
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
    private val fetchDiscoverMovieRxUseCase: FetchDiscoverMovieRxUseCase
) : BaseViewModel() {
    private val _movieList = MutableStateFlow(UiModel(data = arrayListOf<MovieSimple>()))
    val movieList: StateFlow<UiModel<List<MovieSimple>>> = _movieList
    private val _isLastPage = MutableStateFlow(false)
    val isLastPage: StateFlow<Boolean> = _isLastPage

    var actualPage = 1

    init {
        fetchDiscoverMovie()
    }

    fun fetchDiscoverMovie() {
        fetchDiscoverMovieRx()
    }

    fun refreshDiscoverMovie() {
        refreshDiscoverMovieRx()
    }

    private fun fetchDiscoverMovieRx() {
        viewModelScope.launch {
            if (_isLastPage.value) return@launch
            _movieList.value = _movieList.value.copy(isLoading = true)
            disposables += fetchDiscoverMovieRxUseCase(actualPage)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
                .subscribe({ discoverMoviePage ->
                    val oldList = _movieList.value.data
                    val newList = discoverMoviePage.results.map { discoverMovie -> MovieSimple(id = discoverMovie.id, title = discoverMovie.title) }
                    _movieList.value = _movieList.value.copy(isLoading = false, data = arrayListOf<MovieSimple>().apply {
                        addAll(oldList)
                        addAll(newList)
                    })
                    _isLastPage.value = actualPage == discoverMoviePage.totalPages
                    actualPage++
                }, { throwable ->
                    _movieList.value = _movieList.value.copy(isLoading = false, error = Consumable(Unit))
                })
        }
    }

    private fun refreshDiscoverMovieRx() {
        viewModelScope.launch {
            _movieList.value = _movieList.value.copy(data = arrayListOf(), isLoading = true)
            actualPage = 1
            _isLastPage.value = false
            fetchDiscoverMovie()
        }
    }
}