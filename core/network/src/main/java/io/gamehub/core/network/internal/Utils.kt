package io.gamehub.core.network.internal

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.gamehub.core.network.RawgApiKeyProvider
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal const val RAWG_API_URL = "https://api.rawg.io/api/"

internal fun defaultJson() = Json {
    ignoreUnknownKeys = true
}

internal fun OkHttpClient.Builder.addApiKeyInterceptor(
    apiKeyProvider: RawgApiKeyProvider,
): OkHttpClient.Builder {
    return addInterceptor(ApiKeyInterceptor(apiKeyProvider))
}

@OptIn(ExperimentalSerializationApi::class)
internal fun retrofit(
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(RAWG_API_URL)
        client(okHttpClient)
        addCallAdapterFactory(EitherCallAdapterFactory())
        addConverterFactory(defaultJson().asConverterFactory("application/json".toMediaType()))
    }.build()
}
