package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.repository.VideoRepository
import javax.inject.Inject

class FetchMovieUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchMovieUseCase {
    override suspend fun invoke(movieId: Int): ResultWrapper<MovieInfo> = videoRepository.fetchMovieInfo(movieId)
}