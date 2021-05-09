package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import io.reactivex.rxjava3.core.Observable

interface FetchConfigurationTmbdRxUseCase {
    operator fun invoke(): Observable<Configuration>
}