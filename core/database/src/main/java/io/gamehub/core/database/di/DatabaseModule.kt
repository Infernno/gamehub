package io.gamehub.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.gamehub.core.database.AppDatabase
import io.gamehub.core.database.dao.GameDetailsDao
import io.gamehub.core.database.dao.GameShortsDao
import io.gamehub.core.database.dao.GenreDao
import io.gamehub.core.database.dao.ScreenshotDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    private const val DATABASE_NAME = "gamehub-db"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideGameDetailsDao(appDatabase: AppDatabase): GameDetailsDao {
        return appDatabase.provideGameDetailsDao()
    }

    @Provides
    fun provideGameShortsDao(appDatabase: AppDatabase): GameShortsDao {
        return appDatabase.provideGameShortsDao()
    }

    @Provides
    fun provideScreenshotDao(appDatabase: AppDatabase): ScreenshotDao {
        return appDatabase.provideScreenshotDao()
    }

    @Provides
    fun provideGenreDao(appDatabase: AppDatabase): GenreDao {
        return appDatabase.provideGenreDao()
    }
}
