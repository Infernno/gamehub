package io.gamehub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.gamehub.core.database.entity.ScreenshotEntity

@Dao
interface ScreenshotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ScreenshotEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ScreenshotEntity>)

    @Query("SELECT * FROM ${ScreenshotEntity.TABLE_NAME} where gameSlug = :slug")
    suspend fun getForGames(slug: String): List<ScreenshotEntity>?
}
