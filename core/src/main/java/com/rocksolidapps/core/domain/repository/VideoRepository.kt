package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import io.reactivex.rxjava3.core.Observable

interface VideoRepository {
    suspend fun fetchDiscoverMovie(page: Int, language: String): DiscoverMoviePages
    suspend fun getMovieInfo(movieId: Int, language: String?): MovieInfo
    fun fetchDiscoverMovieRx(page: Int, language: String): Observable<DiscoverMoviePages>
    fun getMovieInfoRx(movieId: Int, language: String?): Observable<MovieInfo>
}