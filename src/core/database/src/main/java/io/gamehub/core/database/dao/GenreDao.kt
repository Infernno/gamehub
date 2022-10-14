package io.gamehub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.gamehub.core.database.entity.GenreEntity

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<GenreEntity>)

    @Query("SELECT * FROM ${GenreEntity.TABLE_NAME} ORDER BY name")
    suspend fun getAll(): List<GenreEntity>?
}
