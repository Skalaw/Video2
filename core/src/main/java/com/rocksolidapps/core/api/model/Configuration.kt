package com.rocksolidapps.core.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Configuration(val images: Images)

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "secure_base_url") val baseUrl: String,
    @Json(name = "backdrop_sizes") val backdropSizes: List<String>,
    @Json(name = "poster_sizes") val posterSizes: List<String>
)