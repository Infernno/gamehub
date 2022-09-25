package io.gamehub.core.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

/**
 * Observe flow events in live data way
 */
fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    lifecycleEvent: Lifecycle.State = Lifecycle.State.STARTED,
    action: FlowCollector<T>,
) {
    val flow = this
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(lifecycleEvent) {
            flow.collect(action)
        }
    }
}
