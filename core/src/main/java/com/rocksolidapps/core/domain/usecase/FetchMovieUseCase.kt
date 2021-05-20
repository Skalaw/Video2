package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.MovieDetailsUi

interface FetchMovieUseCase {
    suspend operator fun invoke(movieId: Int): ResultWrapper<MovieDetailsUi>
}