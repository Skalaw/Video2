package com.rocksolidapps.core.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieInfo(
    val id: Int,
    val title: String
)