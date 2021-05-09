package com.rocksolidapps.movies.data.repository

import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.rocksolidapps.movies.BuildConfig
import java.util.*
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor() : ConfigRepository {
    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
    override val apiKey: String
        get() = BuildConfig.TMDB_API_KEY
    override val language: String
        get() = Locale.getDefault().language
    override val useRxJava: Boolean
        get() = true
}