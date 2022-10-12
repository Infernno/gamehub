package io.gamehub.data.games.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.data.games.repository.GameRepository
import io.gamehub.data.games.repository.GameRepositoryImpl
import io.gamehub.data.games.repository.GenreRepository
import io.gamehub.data.games.repository.GenreRepositoryImpl
import io.gamehub.data.games.repository.ScreenshotRepository
import io.gamehub.data.games.repository.ScreenshotRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface GamesDataModule {

    @Binds
    @Singleton
    fun bindGameRepository(impl: GameRepositoryImpl): GameRepository

    @Binds
    @Singleton
    fun bindScreenshotRepository(impl: ScreenshotRepositoryImpl): ScreenshotRepository


    @Binds
    fun bindGenreRepository(impl: GenreRepositoryImpl): GenreRepository
}
