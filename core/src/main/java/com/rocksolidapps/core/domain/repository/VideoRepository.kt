package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieDetails
import com.rocksolidapps.core.api.network.ResultWrapper
import io.reactivex.rxjava3.core.Observable

interface VideoRepository {
    suspend fun fetchConfiguration(): ResultWrapper<Configuration>
    suspend fun fetchDiscoverMovie(page: Int): ResultWrapper<DiscoverMoviePages>
    suspend fun fetchMovieDetails(movieId: Int): ResultWrapper<MovieDetails>
    fun fetchConfigurationRx(): Observable<Configuration>
    fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages>
    fun fetchMovieDetailsRx(movieId: Int): Observable<MovieDetails>
}