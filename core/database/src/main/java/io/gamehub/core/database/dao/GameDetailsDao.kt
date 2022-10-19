package io.gamehub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.gamehub.core.database.entity.GameDetailsEntity

@Dao
interface GameDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: GameDetailsEntity)

    @Query("SELECT * FROM ${GameDetailsEntity.TABLE_NAME} where slug = :slug")
    suspend fun getBySlug(slug: String): GameDetailsEntity?
}
