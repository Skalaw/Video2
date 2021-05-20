package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.domain.model.DiscoverMoviePageUi
import io.reactivex.rxjava3.core.Observable

interface FetchDiscoverMovieRxUseCase {
    operator fun invoke(page: Int): Observable<DiscoverMoviePageUi>
}