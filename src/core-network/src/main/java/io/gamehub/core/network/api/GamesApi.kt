package io.gamehub.core.network.api

import arrow.retrofit.adapter.either.networkhandling.CallError
import okhttp3.OkHttpClient
import retrofit2.create
import arrow.core.Either
import io.gamehub.core.network.dto.BaseResponse
import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.core.network.internal.retrofit

import retrofit2.http.GET

interface GamesApi {

    @GET("/api/games")
    suspend fun getAll(): Either<CallError, BaseResponse<GameShortDto>>
}

internal fun makeGamesApi(
    okHttpClient: OkHttpClient
): GamesApi = retrofit(okHttpClient).create()
