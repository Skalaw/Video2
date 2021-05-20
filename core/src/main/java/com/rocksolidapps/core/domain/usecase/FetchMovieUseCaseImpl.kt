package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.network.ResultWrapper
import com.rocksolidapps.core.domain.model.MovieDetailsUi
import com.rocksolidapps.core.domain.repository.VideoRepository
import javax.inject.Inject

class FetchMovieUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchMovieUseCase {
    override suspend fun invoke(movieId: Int): ResultWrapper<MovieDetailsUi> {
        val configurationWrapper = videoRepository.fetchConfiguration()
        if (configurationWrapper is ResultWrapper.GenericError) {
            return configurationWrapper
        }
        if (configurationWrapper is ResultWrapper.NetworkError) {
            return configurationWrapper
        }
        val movieDetailsWrapper = videoRepository.fetchMovieDetails(movieId)
        if (movieDetailsWrapper is ResultWrapper.GenericError) {
            return movieDetailsWrapper
        }
        if (movieDetailsWrapper is ResultWrapper.NetworkError) {
            return movieDetailsWrapper
        }
        // TODO: this thing above looks ugly

        if (configurationWrapper is ResultWrapper.Success && movieDetailsWrapper is ResultWrapper.Success) {
            val configuration = configurationWrapper.value
            val movieDetails = movieDetailsWrapper.value
            val prefixPoster = configuration.images.baseUrl + configuration.images.posterSizes[0] // TODO: think about provide correct posterSizes
            val prefixBackdrop = configuration.images.baseUrl + configuration.images.backdropSizes[0] // TODO: think about provide correct backdropSizes

            return ResultWrapper.Success(
                MovieDetailsUi(
                    id = movieDetails.id,
                    title = movieDetails.title,
                    overview = movieDetails.overview,
                    posterPath = prefixPoster.plus(movieDetails.posterPath),
                    backdropPath = prefixBackdrop.plus(movieDetails.backdropPath)
                )
            )
        }
        throw IllegalStateException()
    }
}