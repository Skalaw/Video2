package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.DiscoverMoviePages
import com.rocksolidapps.core.domain.model.DiscoverMoviePage
import com.rocksolidapps.core.domain.model.MovieSimple
import com.rocksolidapps.core.domain.repository.VideoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchDiscoverMovieRxUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchDiscoverMovieRxUseCase {
    override fun invoke(page: Int): Observable<DiscoverMoviePage> = Observable.zip(
        videoRepository.fetchConfigurationRx(),
        videoRepository.fetchDiscoverMovieRx(page), { configuration: Configuration, discoverMoviePages: DiscoverMoviePages ->
            val prefixPoster = configuration.images.baseUrl + configuration.images.posterSizes[0] // TODO: think about provide correct posterSizes
            DiscoverMoviePage(
                page = discoverMoviePages.page,
                items = discoverMoviePages.results.map { discoverMovie ->
                    MovieSimple(
                        id = discoverMovie.id,
                        title = discoverMovie.title,
                        overview = discoverMovie.overview,
                        urlPoster = prefixPoster + discoverMovie.posterPath
                    )
                },
                totalPages = discoverMoviePages.totalPages
            )
        })
}