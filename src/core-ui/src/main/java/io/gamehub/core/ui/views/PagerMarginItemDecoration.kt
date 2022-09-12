package io.gamehub.core.ui.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class PagerMarginItemDecoration(
    private val horizontalMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = horizontalMargin
        outRect.left = horizontalMargin
    }
}
