package io.gamehub.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.gamehub.core.database.entity.GameDetailsEntity

@Database(
    entities = [
        GameDetailsEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
}
