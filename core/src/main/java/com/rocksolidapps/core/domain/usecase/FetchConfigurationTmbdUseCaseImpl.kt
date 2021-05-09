package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.repository.VideoRepository
import javax.inject.Inject

class FetchConfigurationTmbdUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchConfigurationTmbdUseCase {
    override suspend fun invoke(): ResultWrapper<Configuration> = videoRepository.fetchConfiguration()
}