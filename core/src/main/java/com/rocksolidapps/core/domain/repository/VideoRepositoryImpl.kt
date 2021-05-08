package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.api.rest.TmdbRest
import io.reactivex.rxjava3.core.Observable

class VideoRepositoryImpl(private val tmdbRest: TmdbRest) : VideoRepository {
    override suspend fun fetchDiscoverMovie(page: Int): DiscoverMoviePages {
        return tmdbRest.fetchDiscoverMovie(page)
    }

    override suspend fun fetchMovieInfo(movieId: Int): MovieInfo {
        return tmdbRest.getMovieInfo(movieId)
    }

    override fun fetchDiscoverMovieRx(page: Int): Observable<DiscoverMoviePages> {
        return tmdbRest.fetchDiscoverMovieRx(page)
    }

    override fun fetchMovieInfoRx(movieId: Int): Observable<MovieInfo> {
        return tmdbRest.getMovieInfoRx(movieId)
    }
}