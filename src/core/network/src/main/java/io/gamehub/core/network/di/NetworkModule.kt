package io.gamehub.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.core.network.BuildConfig
import io.gamehub.core.network.RawgApiKeyProvider
import io.gamehub.core.network.api.RawgApi
import io.gamehub.core.network.internal.addApiKeyInterceptor
import io.gamehub.core.network.internal.retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyProvider: RawgApiKeyProvider): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addApiKeyInterceptor(apiKeyProvider)
            .also {
                if (BuildConfig.DEBUG) {
                    val interceptor =
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    it.addInterceptor(interceptor)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRawgApi(httpClient: OkHttpClient): RawgApi {
        return retrofit(httpClient).create()
    }
}
