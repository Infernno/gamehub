package io.gamehub.core.utils.extensions

import kotlinx.coroutines.CancellationException

fun Throwable.isFatal(): Boolean {
    return when (this) {
        is Error -> true
        is CancellationException -> true
        else -> false
    }
}

fun Throwable.nonFatalOrThrow(): Throwable {
    return if (isFatal()) throw this else this
}
