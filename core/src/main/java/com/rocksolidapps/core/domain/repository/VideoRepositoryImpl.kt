package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieDetails
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.api.network.safeApiCall
import com.rocksolidapps.core.api.rest.TmdbRest
import com.rocksolidapps.core.di.IoDispatcher
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val tmdbRest: TmdbRest,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : VideoRepository {
    override suspend fun fetchConfiguration(): ResultWrapper<Configuration> {
        return safeApiCall(dispatcher) {
            tmdbRest.fetchConfiguration()
        }
    }

    override suspend fun fetchDiscoverMovie(page: Int): ResultWrapper<DiscoverMoviePages> {
        return safeApiCall(dispatcher) {
            tmdbRest.fetchDiscoverMovie(page)
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): ResultWrapper<MovieDetails> {
        return safeApiCall(dispatcher) {
            tmdbRest.getMovieDetails(movieId)
        }
    }

    override fun fetchConfigurationRx(): Observable<Configuration> {
        return tmdbRest.fetchConfigurationRx()
    }

    override fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages> {
        return tmdbRest.fetchDiscoverMovieRx(page)
    }

    override fun fetchMovieDetailsRx(movieId: Int): Observable<MovieDetails> {
        return tmdbRest.getMovieDetailsRx(movieId)
    }
}