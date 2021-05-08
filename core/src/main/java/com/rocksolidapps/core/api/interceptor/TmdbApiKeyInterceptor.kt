package com.rocksolidapps.core.api.interceptor

import com.rocksolidapps.core.domain.repository.ConfigRepository
import okhttp3.Interceptor
import okhttp3.Response

class TmdbApiKeyInterceptor(private val configRepository: ConfigRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder().addQueryParameter(QUERY_API_KEY, configRepository.apiKey).build()
        return chain.proceed(original.newBuilder().url(url).build())
    }

    companion object {
        private const val QUERY_API_KEY = "api_key"
    }
}