package com.rocksolidapps.core.api.rest

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieDetails
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbRest {
    @GET("configuration")
    suspend fun fetchConfiguration(): Configuration

    @GET("discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("page") page: Int
    ): DiscoverMoviePages

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieDetails

    @GET("configuration")
    fun fetchConfigurationRx(): Observable<Configuration>

    @GET("discover/movie")
    fun fetchDiscoverMovieRx(
        @Query("page") page: Int
    ): Observable<DiscoverMoviePages>

    @GET("movie/{movieId}")
    fun getMovieDetailsRx(
        @Path("movieId") movieId: Int
    ): Observable<MovieDetails>
}