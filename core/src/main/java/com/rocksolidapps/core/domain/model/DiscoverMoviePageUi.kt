package com.rocksolidapps.core.domain.model

data class DiscoverMoviePageUi(
    val page: Int,
    val items: List<DiscoverMovieUi>,
    val totalPages: Int
)