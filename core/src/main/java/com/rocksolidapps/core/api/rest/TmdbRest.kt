package com.rocksolidapps.core.api.rest

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbRest {
    @GET("discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("page") page: Int
    ): DiscoverMoviePages

    @GET("movie/{movieId}")
    suspend fun getMovieInfo(
        @Path("movieId") movieId: Int
    ): MovieInfo

    @GET("discover/movie")
    fun fetchDiscoverMovieRx(
        @Query("page") page: Int
    ): Observable<DiscoverMoviePages>

    @GET("movie/{movieId}")
    fun getMovieInfoRx(
        @Path("movieId") movieId: Int
    ): Observable<MovieInfo>
}