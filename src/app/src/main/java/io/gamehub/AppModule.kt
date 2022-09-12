package io.gamehub

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.core.network.RawgApiKeyProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiKeyProvider(): RawgApiKeyProvider {
        return object : RawgApiKeyProvider {
            override val apiKey: String
                get() = BuildConfig.RAWG_API_KEY
        }
    }
}
