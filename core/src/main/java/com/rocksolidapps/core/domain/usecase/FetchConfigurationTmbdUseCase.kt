package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.network.ResultWrapper

interface FetchConfigurationTmbdUseCase {
    suspend operator fun invoke(): ResultWrapper<Configuration>
}