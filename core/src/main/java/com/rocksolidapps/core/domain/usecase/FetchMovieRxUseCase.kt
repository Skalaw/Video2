package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.domain.model.MovieDetailsUi
import io.reactivex.rxjava3.core.Observable

interface FetchMovieRxUseCase {
    operator fun invoke(movieId: Int): Observable<MovieDetailsUi>
}