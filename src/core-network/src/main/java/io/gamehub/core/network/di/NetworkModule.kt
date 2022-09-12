package io.gamehub.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.core.network.BuildConfig
import io.gamehub.core.network.RawgApiKeyProvider
import io.gamehub.core.network.api.GamesApi
import io.gamehub.core.network.api.makeGamesApi
import io.gamehub.core.network.internal.addApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                if(BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    it.addInterceptor(interceptor)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideGamesApi(httpClient: OkHttpClient): GamesApi {
        return makeGamesApi(httpClient)
    }
}
