package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.domain.repository.VideoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchDiscoverMovieRxUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchDiscoverMovieRxUseCase {
    override fun invoke(page: Int): Observable<DiscoverMoviePages> = videoRepository.fetchDiscoverMovieRx(page)
}