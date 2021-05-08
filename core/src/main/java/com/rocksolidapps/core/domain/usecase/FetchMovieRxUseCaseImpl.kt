package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.MovieInfo
import com.rocksolidapps.core.domain.repository.VideoRepository
import io.reactivex.rxjava3.core.Observable

class FetchMovieRxUseCaseImpl(private val videoRepository: VideoRepository) : FetchMovieRxUseCase {
    override fun invoke(movieId: Int): Observable<MovieInfo> = videoRepository.fetchMovieInfoRx(movieId)
}