package io.gamehub.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = GameDetailsEntity.TABLE_NAME)
data class GameDetailsEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nameOriginal") val nameOriginal: String,
    @ColumnInfo(name = "descriptionRaw") val descriptionRaw: String,
    @ColumnInfo(name = "metacritic")  val metacritic: Int?,
    @ColumnInfo(name = "released")  val released: LocalDate?,
    @ColumnInfo(name = "tba")  val tba: Boolean?,
    @ColumnInfo(name = "backgroundImageUrl")  val backgroundImageUrl: String?,
    @ColumnInfo(name = "websiteUrl")  val websiteUrl: String?,
    @ColumnInfo(name = "playtime")  val playtime: Int?,
    @ColumnInfo(name = "screenshotsCount")  val screenshotsCount: Int?,
    @ColumnInfo(name = "genres")  val genres: List<String>,
    @ColumnInfo(name = "developers") val developers: List<String>,
    @ColumnInfo(name = "publishers")  val publishers: List<String>,
    @ColumnInfo(name = "stores") val stores: List<String>,
    @ColumnInfo(name = "platforms")  val platforms: List<String>,
) {
    internal companion object {
        const val TABLE_NAME = "game_details_table"
    }
}
