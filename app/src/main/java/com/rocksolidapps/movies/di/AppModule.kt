package com.rocksolidapps.movies.di

import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.core.domain.repository.VideoRepository
import com.rocksolidapps.core.domain.repository.VideoRepositoryImpl
import com.rocksolidapps.movies.data.repository.ConfigRepositoryImpl
import com.rocksolidapps.movies.data.repository.SchedulersInjector
import com.rocksolidapps.movies.data.repository.SchedulersInjectorImpl
import dagger.Binds
import dagger.Module

@Module(includes = [TmdbModule::class])
interface AppModule {
    @Binds
    fun bindSchedulersInjector(schedulersInjector: SchedulersInjectorImpl): SchedulersInjector

    @Binds
    fun bindConfigRepository(configRepository: ConfigRepositoryImpl): ConfigRepository

    @Binds
    fun bindVideoRepository(videoRepository: VideoRepositoryImpl): VideoRepository
}