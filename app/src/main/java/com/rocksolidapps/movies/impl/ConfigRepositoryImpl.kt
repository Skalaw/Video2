package com.rocksolidapps.movies.impl

import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.movies.BuildConfig

class ConfigRepositoryImpl : ConfigRepository {
    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
    override val apiKey: String
        get() = BuildConfig.TMDB_API_KEY
}