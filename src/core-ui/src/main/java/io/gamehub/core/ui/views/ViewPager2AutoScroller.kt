package io.gamehub.core.ui.views

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.*

@SuppressLint("ClickableViewAccessibility")
internal class ViewPager2AutoScroller(
    private val viewPager: ViewPager2,
    private val scope: CoroutineScope,
    private val intervalMs: Long
) : View.OnTouchListener {

    private var jobHandle: Job? = null

    init {
        start()
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> stop()
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> start()
        }

        return false
    }

    private fun start() {
        jobHandle?.cancel()
        jobHandle = scope.launch {
            while (isActive) {
                delay(intervalMs)

                val numberOfItems = viewPager.adapter?.itemCount ?: 0
                val currentItem = viewPager.currentItem

                val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
                val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1

                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }

    private fun stop() {
        jobHandle?.cancel()
        jobHandle = null
    }
}
