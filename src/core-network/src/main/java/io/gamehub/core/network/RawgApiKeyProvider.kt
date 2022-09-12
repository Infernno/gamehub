package io.gamehub.core.network

interface RawgApiKeyProvider {
    val apiKey: String?
}

fun RawgApiKeyProvider.requireApiKey(): String {
    return checkNotNull(apiKey) {
        "Api key is not found"
    }
}
