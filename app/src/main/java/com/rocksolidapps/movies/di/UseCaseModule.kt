package com.rocksolidapps.movies.di

import com.rocksolidapps.core.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun provideFetchDiscoverMovieRxUseCase(fetchDiscoverMovieRxUseCase: FetchDiscoverMovieRxUseCaseImpl): FetchDiscoverMovieRxUseCase

    @Binds
    fun provideFetchMovieRxUseCase(fetchMovieRxUseCase: FetchMovieRxUseCaseImpl): FetchMovieRxUseCase

    @Binds
    fun provideFetchDiscoverMovieUseCase(fetchDiscoverMovieUseCase: FetchDiscoverMovieUseCaseImpl): FetchDiscoverMovieUseCase

    @Binds
    fun provideFetchMovieUseCase(fetchMovieUseCase: FetchMovieUseCaseImpl): FetchMovieUseCase
}