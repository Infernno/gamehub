package io.gamehub.core.navigation

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.navArgs(
    key: String
): T {
    return checkNotNull(get<T>(key)) {
        "Failed to get navigation argument with key $key"
    }
}
