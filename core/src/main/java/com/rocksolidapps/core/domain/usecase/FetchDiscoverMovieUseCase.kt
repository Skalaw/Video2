package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.network.ResultWrapper

interface FetchDiscoverMovieUseCase {
    suspend operator fun invoke(page: Int): ResultWrapper<DiscoverMoviePages>
}