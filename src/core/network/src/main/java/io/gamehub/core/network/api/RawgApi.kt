package io.gamehub.core.network.api

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import io.gamehub.core.network.dto.BaseResponse
import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.core.network.dto.GenreDto
import io.gamehub.core.network.dto.ScreenshotDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {

    /**
     * Get a list of games.
     * @property page integer A page number within the paginated result set.
     * @property pageSize integer Number of results to return per page.
     * @property search string Search query.
     * @property parentPlatform string Filter by parent platforms, for example: 1,2,3.
     * @property platforms string Filter by platforms, for example: 4,5.
     * @property stores string Filter by stores, for example: 5,6.
     * @property developers string Filter by developers, for example: 1612,18893 or valve-software,feral-interactive.
     * @property publishers string Filter by publishers, for example: 354,20987 or electronic-arts,microsoft-studios.
     * @property genres string Filter by genres, for example: 4,51 or action,indie.
     * @property tags string Filter by tags, for example: 31,7 or singleplayer,multiplayer.
     * @property creators string Filter by creators, for example: 78,28 or cris-velasco,mike-morasky.
     * @property dates string Filter by a release date, for example: 2010-01-01,2018-12-31.1960-01-01,1969-12-31.
     * @property platformsCount integer Filter by platforms count, for example: 1.
     * @property excludeCollection integer Exclude games from a particular collection, for example: 123.
     * @property excludeAdditions boolean Exclude additions.
     * @property excludeParents boolean Exclude games which have additions.
     * @property excludeGameSeries boolean Exclude games which included in a game series.
     * @property ordering string Available fields: name, released, added, created, rating.
     * You can reverse the sort order adding a hyphen, for example: -released.
     *
     */
    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("search") search: String? = null,
        @Query("parent_platforms") parentPlatform: String? = null,
        @Query("platforms") platforms: String? = null,
        @Query("stores") stores: String? = null,
        @Query("developers") developers: String? = null,
        @Query("publishers") publishers: String? = null,
        @Query("genres") genres: String? = null,
        @Query("tags") tags: String? = null,
        @Query("creators") creators: String? = null,
        @Query("dates") dates: String? = null,
        @Query("platforms_count") platformsCount: Int? = null,
        @Query("exclude_collection") excludeCollection: Int? = null,
        @Query("exclude_additions") excludeAdditions: Boolean? = null,
        @Query("exclude_parents") excludeParents: Boolean? = null,
        @Query("exclude_game_series") excludeGameSeries: Boolean? = null,
        @Query("ordering") ordering: String? = null,
    ): Either<CallError, BaseResponse<GameShortDto>>

    /**
     * Get screenshots for the game.
     */
    @GET("games/{game_pk}/screenshots")
    suspend fun getScreenshotsOfTheGame(
        @Path("game_pk") slug: String,
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
    ): Either<CallError, BaseResponse<ScreenshotDto>>

    /**
     * Get a list of video game genres.
     */
    @GET("genres")
    suspend fun getGenres(
        @Query("ordering") ordering: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): Either<CallError, BaseResponse<GenreDto>>

    /**
     * Get details of the genre.
     */
    @GET("genres/{id}")
    suspend fun fetchGenreDetails(
        @Path("id") id: Int
    ): Either<CallError, GenreDto>
}
