package com.rocksolidapps.core.api.rest

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.model.MovieInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbRest {
    @GET("discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("page") page: Int,
        @Query("language") language: String
    ): DiscoverMoviePages

    @GET("movie/{movieId}")
    suspend fun getMovieInfo(
        @Path("movieId") movieId: Int,
        @Query("language") language: String?
    ): MovieInfo
}