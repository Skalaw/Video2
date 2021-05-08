package com.rocksolidapps.movies.di

import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchDiscoverMovieRxUseCaseImpl
import com.rocksolidapps.core.domain.usecase.FetchMovieRxUseCase
import com.rocksolidapps.core.domain.usecase.FetchMovieRxUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun provideFetchDiscoverMovieRxUseCase(fetchDiscoverMovieRxUseCase: FetchDiscoverMovieRxUseCaseImpl): FetchDiscoverMovieRxUseCase

    @Binds
    fun provideFetchMovieRxUseCase(fetchMovieRxUseCase: FetchMovieRxUseCaseImpl): FetchMovieRxUseCase
}