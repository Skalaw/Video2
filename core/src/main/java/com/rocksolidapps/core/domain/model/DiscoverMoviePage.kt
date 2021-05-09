package com.rocksolidapps.core.domain.model

data class DiscoverMoviePage(
    val page: Int,
    val items: List<MovieSimple>,
    val totalPages: Int
)