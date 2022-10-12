package io.gamehub.core.common.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

suspend fun <T> executeSuspendSafe(callback: suspend () -> T): Result<T> {
    return try {
        Result.success(callback())
    } catch (th: Throwable) {
        Result.failure(th.nonFatalOrThrow())
    }
}

suspend fun <T> executeSuspendAsyncSafe(callback: suspend CoroutineScope.() -> T): Result<T> {
    return executeSuspendSafe {
        // Note: callback is wrapped in coroutineScope so its possible to catch exceptions from async { } and stop here
        // Otherwise this would crash the app
        coroutineScope {
            callback(this)
        }
    }
}
