package com.rocksolidapps.core.api.model

import com.squareup.moshi.Json

class DiscoverMoviePages(
    val page: Int,
    val result: List<DiscoverMovie>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)

data class DiscoverMovie(
    val id: Int,
    val title: String,
    @Json(name = "poster_path") val posterPath: String
)