package io.gamehub.core.network.internal

import io.gamehub.core.network.RawgApiKeyProvider
import io.gamehub.core.network.requireApiKey
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(
    private val apiKeyProvider: RawgApiKeyProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter(API_KEY, apiKeyProvider.requireApiKey())
            .build()

        val newRequest = request
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }

    private companion object {
        const val API_KEY = "key"
    }
}
