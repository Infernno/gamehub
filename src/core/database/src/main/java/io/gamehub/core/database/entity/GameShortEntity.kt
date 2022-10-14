package io.gamehub.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = GameShortEntity.TABLE_NAME)
data class GameShortEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "metacritic") val metacritic: Int?,
    @ColumnInfo(name = "genres") val genres: List<String>,
    @ColumnInfo(name = "screenshots") val screenshots: List<String>,
    @ColumnInfo(name = "platforms") val platforms: List<String>,
    @ColumnInfo(name = "stores") val stores: List<String>,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: LocalDate?
) {
    internal companion object {
        const val TABLE_NAME = "game_shorts_table"
    }
}
