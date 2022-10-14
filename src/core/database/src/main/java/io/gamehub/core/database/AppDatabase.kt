package io.gamehub.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.gamehub.core.database.converters.ArrayStringConverter
import io.gamehub.core.database.converters.LocalDateConverter
import io.gamehub.core.database.dao.GameDetailsDao
import io.gamehub.core.database.dao.GameShortsDao
import io.gamehub.core.database.dao.GenreDao
import io.gamehub.core.database.dao.ScreenshotDao
import io.gamehub.core.database.entity.GameDetailsEntity
import io.gamehub.core.database.entity.GameShortEntity
import io.gamehub.core.database.entity.GenreEntity
import io.gamehub.core.database.entity.ScreenshotEntity

@Database(
    entities = [
        GameShortEntity::class,
        GameDetailsEntity::class,
        ScreenshotEntity::class,
        GenreEntity::class,

    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    value = [
        ArrayStringConverter::class,
        LocalDateConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun provideGameDetailsDao(): GameDetailsDao

    abstract fun provideScreenshotDao(): ScreenshotDao

    abstract fun provideGenreDao(): GenreDao

    abstract fun provideGameShortsDao(): GameShortsDao
}
