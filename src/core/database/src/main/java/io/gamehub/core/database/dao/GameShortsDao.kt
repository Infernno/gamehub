package io.gamehub.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.gamehub.core.database.entity.GameShortEntity

@Dao
interface GameShortsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<GameShortEntity>)

    @Query("SELECT * FROM ${GameShortEntity.TABLE_NAME} WHERE releaseDate BETWEEN :start AND :end ORDER BY releaseDate DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getGamesByDate(
        start: Long,
        end: Long,
        pageSize: Int,
        offset: Int,
    ): List<GameShortEntity>?

    @Query("SELECT * FROM ${GameShortEntity.TABLE_NAME} WHERE releaseDate BETWEEN :start AND :end ORDER BY metacritic DESC LIMIT :pageSize OFFSET :offset")
    suspend fun getPopularGames(
        start: Long,
        end: Long,
        pageSize: Int,
        offset: Int,
    ): List<GameShortEntity>?

    @Query("SELECT * FROM ${GameShortEntity.TABLE_NAME} WHERE genres LIKE '%' || :genre || '%' LIMIT :pageSize OFFSET :offset")
    suspend fun getGamesByGenre(
        genre: String,
        pageSize: Int,
        offset: Int,
    ): List<GameShortEntity>?

    @Query("SELECT * FROM ${GameShortEntity.TABLE_NAME} WHERE name LIKE '%' || :title || '%' LIMIT :pageSize OFFSET :offset")
    suspend fun getGamesByName(
        title: String,
        pageSize: Int,
        offset: Int,
    ): List<GameShortEntity>?
}
