package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.DiscoverMoviePageUi

interface FetchDiscoverMovieUseCase {
    suspend operator fun invoke(page: Int): ResultWrapper<DiscoverMoviePageUi>
}