package com.rocksolidapps.core.domain.repository

interface ConfigRepository {
    val isDebug: Boolean
    val apiKey: String
    val language: String
}