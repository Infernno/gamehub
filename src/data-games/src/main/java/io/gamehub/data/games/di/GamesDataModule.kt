package io.gamehub.data.games.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.data.games.repository.FakeGameRepository
import io.gamehub.data.games.repository.GameRepository
import io.gamehub.data.games.repository.GameRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface GamesDataModule {

    @Binds
    @Singleton
    fun bindGameRepository(impl: GameRepositoryImpl): GameRepository
}
