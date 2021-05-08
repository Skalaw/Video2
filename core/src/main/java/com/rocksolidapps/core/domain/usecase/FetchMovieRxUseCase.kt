package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.MovieInfo
import io.reactivex.rxjava3.core.Observable

interface FetchMovieRxUseCase {
    operator fun invoke(movieId: Int): Observable<MovieInfo>
}