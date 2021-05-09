package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.domain.repository.VideoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchConfigurationTmbdRxUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchConfigurationTmbdRxUseCase {
    override fun invoke(): Observable<Configuration> = videoRepository.fetchConfigurationRx()
}