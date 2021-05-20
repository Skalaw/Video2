package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.DiscoverMoviePageUi
import com.rocksolidapps.core.domain.model.DiscoverMovieUi
import com.rocksolidapps.core.domain.repository.VideoRepository
import javax.inject.Inject

class FetchDiscoverMovieUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchDiscoverMovieUseCase {
    override suspend fun invoke(page: Int): ResultWrapper<DiscoverMoviePageUi> {
        val configurationWrapper = videoRepository.fetchConfiguration()
        if (configurationWrapper is ResultWrapper.GenericError) {
            return configurationWrapper
        }
        if (configurationWrapper is ResultWrapper.NetworkError) {
            return configurationWrapper
        }
        val discoverMoviePagesWrapper = videoRepository.fetchDiscoverMovie(page)
        if (discoverMoviePagesWrapper is ResultWrapper.GenericError) {
            return discoverMoviePagesWrapper
        }
        if (discoverMoviePagesWrapper is ResultWrapper.NetworkError) {
            return discoverMoviePagesWrapper
        }
        // TODO: this thing above looks ugly

        if (configurationWrapper is ResultWrapper.Success && discoverMoviePagesWrapper is ResultWrapper.Success) {
            val configuration = configurationWrapper.value
            val discoverMoviePages = discoverMoviePagesWrapper.value
            val prefixPoster = configuration.images.baseUrl + configuration.images.posterSizes[0] // TODO: think about provide correct posterSizes

            return ResultWrapper.Success(
                DiscoverMoviePageUi(
                    page = discoverMoviePages.page,
                    items = discoverMoviePages.results.map { discoverMovie ->
                        DiscoverMovieUi(
                            id = discoverMovie.id,
                            title = discoverMovie.title,
                            overview = discoverMovie.overview,
                            urlPoster = prefixPoster + discoverMovie.posterPath
                        )
                    },
                    totalPages = discoverMoviePages.totalPages
                )
            )
        }
        throw IllegalStateException()
    }
}