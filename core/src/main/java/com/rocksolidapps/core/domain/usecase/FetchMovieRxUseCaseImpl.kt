package com.rocksolidapps.core.domain.usecase

import com.rocksolidapps.core.api.model.Configuration
import com.rocksolidapps.core.api.model.MovieDetails
import com.rocksolidapps.core.domain.model.MovieDetailsUi
import com.rocksolidapps.core.domain.repository.VideoRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FetchMovieRxUseCaseImpl @Inject constructor(private val videoRepository: VideoRepository) : FetchMovieRxUseCase {
    override fun invoke(movieId: Int): Observable<MovieDetailsUi> = Observable.zip(
        videoRepository.fetchConfigurationRx(),
        videoRepository.fetchMovieDetailsRx(movieId), { configuration: Configuration, movieDetails: MovieDetails ->
            val prefixPoster = configuration.images.baseUrl + configuration.images.posterSizes[0] // TODO: think about provide correct posterSizes
            val prefixBackdrop = configuration.images.baseUrl + configuration.images.backdropSizes[0] // TODO: think about provide correct backdropSizes
            MovieDetailsUi(
                id = movieDetails.id,
                title = movieDetails.title,
                overview = movieDetails.overview,
                posterPath = prefixPoster.plus(movieDetails.posterPath),
                backdropPath = prefixBackdrop.plus(movieDetails.backdropPath)
            )
        })
}