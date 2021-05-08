package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import io.reactivex.rxjava3.core.Observable

interface VideoRepository {
    suspend fun fetchDiscoverMovie(page: Int): DiscoverMoviePages
    suspend fun fetchMovieInfo(movieId: Int): MovieInfo
    fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages>
    fun fetchMovieInfoRx(movieId: Int): Observable<MovieInfo>
}