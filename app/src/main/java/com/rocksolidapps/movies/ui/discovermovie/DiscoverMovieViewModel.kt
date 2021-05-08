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

class DiscoverMovieViewModel(
    private val schedulers: SchedulersInjector,
    private val fetchDiscoverMovieRxUseCase: FetchDiscoverMovieRxUseCase
) : BaseViewModel() {
    private val _movieList = MutableStateFlow(UiModel(data = arrayListOf<MovieSimple>()))
    val movieList: StateFlow<UiModel<List<MovieSimple>>> = _movieList

    var actualPage = 1
    var isLastPage = false

    fun fetchDiscoverMovieRx() {
        viewModelScope.launch {
            if (isLastPage) return@launch
            _movieList.value.copy(isLoading = true)
            disposables += fetchDiscoverMovieRxUseCase(actualPage)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.ui)
                .subscribe({ discoverMoviePage ->
                    val oldList = _movieList.value.data
                    val newList = discoverMoviePage.result.map { discoverMovie -> MovieSimple(id = discoverMovie.id, title = discoverMovie.title) }
                    _movieList.value = _movieList.value.copy(isLoading = false, data = arrayListOf<MovieSimple>().apply {
                        addAll(oldList)
                        addAll(newList)
                    })
                    isLastPage = actualPage == discoverMoviePage.totalPages
                    actualPage++
                }, { throwable ->
                    _movieList.value.copy(isLoading = false, error = Consumable(Unit))
                })
        }
    }

    fun refreshDiscoverMovieRx() {
        viewModelScope.launch {
            _movieList.value = _movieList.value.copy(data = arrayListOf())
            actualPage = 1
            isLastPage = false
            fetchDiscoverMovieRx()
        }
    }
}