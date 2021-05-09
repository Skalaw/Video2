package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.api.network.ResultWrapper
import io.reactivex.rxjava3.core.Observable

interface VideoRepository {
    suspend fun fetchDiscoverMovie(page: Int): ResultWrapper<DiscoverMoviePages>
    suspend fun fetchMovieInfo(movieId: Int): ResultWrapper<MovieInfo>
    fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages>
    fun fetchMovieInfoRx(movieId: Int): Observable<MovieInfo>
}