package com.rocksolidapps.movies.data.repository

import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.movies.BuildConfig
import java.util.*

class ConfigRepositoryImpl : ConfigRepository {
    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
    override val apiKey: String
        get() = BuildConfig.TMDB_API_KEY
    override val language: String
        get() = Locale.getDefault().language // TODO: verify is working correctly
}