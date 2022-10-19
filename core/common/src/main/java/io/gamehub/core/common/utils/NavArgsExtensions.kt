package io.gamehub.core.common.utils

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.navArgs(
    key: String
): T {
    return checkNotNull(get<T>(key)) {
        "Failed to get navigation argument with key $key"
    }
}
