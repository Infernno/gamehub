package io.gamehub.data.genres.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.gamehub.data.genres.repositories.GenreRepository
import io.gamehub.data.genres.repositories.GenreRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface GenresDataModule {

    @Binds
    fun bindGenreRepository(impl: GenreRepositoryImpl): GenreRepository
}
