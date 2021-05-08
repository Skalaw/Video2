package com.rocksolidapps.core.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DiscoverMoviePages(
    val page: Int,
    val results: List<DiscoverMovie>,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class DiscoverMovie(
    val id: Int,
    val title: String,
    @Json(name = "poster_path") val posterPath: String
)