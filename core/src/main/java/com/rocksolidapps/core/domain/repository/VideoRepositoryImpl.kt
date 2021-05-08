package com.rocksolidapps.core.domain.repository

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.api.rest.TmdbRest
import io.reactivex.rxjava3.core.Observable

class VideoRepositoryImpl(private val tmdbRest: TmdbRest) : VideoRepository {
    override suspend fun fetchDiscoverMovie(page: Int, language: String): DiscoverMoviePages {
        return tmdbRest.fetchDiscoverMovie(page, language)
    }

    override suspend fun getMovieInfo(movieId: Int, language: String?): MovieInfo {
        return tmdbRest.getMovieInfo(movieId, language)
    }

    override fun fetchDiscoverMovieRx(page: Int, language: String): Observable<DiscoverMoviePages> {
        return tmdbRest.fetchDiscoverMovieRx(page, language)
    }

    override fun getMovieInfoRx(movieId: Int, language: String?): Observable<MovieInfo> {
        return tmdbRest.getMovieInfoRx(movieId, language)
    }
}