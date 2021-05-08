package com.rocksolidapps.movies.di

import com.rocksolidapps.core.api.interceptor.TmdbInterceptor
import com.rocksolidapps.core.api.rest.TmdbRest
import com.rocksolidapps.core.domain.repository.ConfigRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [NetworkModule::class])
class TmdbModule {
    @Provides
    fun provideOkHttpClient(configRepository: ConfigRepository): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(TmdbInterceptor(configRepository)) // TmdbInterceptor should be above than HttpLoggingInterceptor for correct logging
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    fun provideTmdbRest(okHttpClient: OkHttpClient, moshi: Moshi): TmdbRest {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(TMDB_ENDPOINT)
            .client(okHttpClient)
            .build()
            .create(TmdbRest::class.java)
    }

    companion object {
        private const val TMDB_ENDPOINT = "https://api.themoviedb.org/3/"
    }
}