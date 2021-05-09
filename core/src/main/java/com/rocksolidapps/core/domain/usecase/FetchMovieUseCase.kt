package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.api.network.ResultWrapper

interface FetchMovieUseCase {
    suspend operator fun invoke(movieId: Int): ResultWrapper<MovieInfo>
}