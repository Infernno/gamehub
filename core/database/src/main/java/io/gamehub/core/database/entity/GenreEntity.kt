package io.gamehub.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GenreEntity.TABLE_NAME)
data class GenreEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "gamesCount") val gamesCount: Int,
) {
    internal companion object {
        const val TABLE_NAME = "genres_table"
    }
}
