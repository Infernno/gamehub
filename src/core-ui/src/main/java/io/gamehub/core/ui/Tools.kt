package io.gamehub.core.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun AttributeSet.onAttributes(
    context: Context,
    @StyleableRes attrs: IntArray,
    callback: TypedArray.() -> Unit
) {
    contract {
        callsInPlace(callback, InvocationKind.EXACTLY_ONCE)
    }

    val attributes = context.obtainStyledAttributes(this, attrs)
    callback(attributes)
    attributes.recycle()
}
