package io.gamehub.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ScreenshotEntity.TABLE_NAME)
data class ScreenshotEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "gameSlug") val gameSlug: String
) {
    internal companion object {
        const val TABLE_NAME = "screenshots_table"
    }
}
