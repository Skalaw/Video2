package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
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

    override suspend fun fetchMovieInfo(movieId: Int): ResultWrapper<MovieInfo> {
        return safeApiCall(dispatcher) {
            tmdbRest.getMovieInfo(movieId)
        }
    }

    override fun fetchConfigurationRx(): Observable<Configuration> {
        return tmdbRest.fetchConfigurationRx()
    }

    override fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages> {
        return tmdbRest.fetchDiscoverMovieRx(page)
    }

    override fun fetchMovieInfoRx(movieId: Int): Observable<MovieInfo> {
        return tmdbRest.getMovieInfoRx(movieId)
    }
}