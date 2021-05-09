package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.repository.VideoRepository
import javax.inject.Inject

class FetchDiscoverMovieUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchDiscoverMovieUseCase {
    override suspend fun invoke(page: Int): ResultWrapper<DiscoverMoviePages> = videoRepository.fetchDiscoverMovie(page)
}