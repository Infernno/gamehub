package io.gamehub.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.gamehub.core.network.RawgApiKeyProvider
import io.gamehub.core.network.api.RawgApi
import io.gamehub.core.network.internal.addApiKeyInterceptor
import io.gamehub.core.network.internal.retrofit
import okhttp3.OkHttpClient
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        apiKeyProvider: RawgApiKeyProvider,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addApiKeyInterceptor(apiKeyProvider)
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()
    }

    @Provides
    @Singleton
    fun provideRawgApi(httpClient: OkHttpClient): RawgApi {
        return retrofit(httpClient).create()
    }
}
