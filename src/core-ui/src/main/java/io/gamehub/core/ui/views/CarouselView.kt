package io.gamehub.core.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import io.gamehub.core.ui.R
import io.gamehub.core.ui.databinding.LayoutCarouselViewBinding
import io.gamehub.core.ui.onAttributes
import kotlinx.coroutines.CoroutineScope

class CarouselView(
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs) {

    private val carouselViewPager: ViewPager2
    //  private val dotsViewPager: ViewPager2

    private val nextItemVisible: Int
    private val currentItemHorizontalMargin: Int

    var adapter: RecyclerView.Adapter<*>?
        get() = carouselViewPager.adapter
        set(value) {
            carouselViewPager.adapter = value
        }

    init {
        orientation = VERTICAL

        val view = LayoutCarouselViewBinding.inflate(LayoutInflater.from(context), this)

        carouselViewPager = view.items
        //  dotsViewPager = view.dots

        attrs.onAttributes(context, R.styleable.CarouselView) {
            nextItemVisible = getDimensionPixelSize(R.styleable.CarouselView_nextItemVisible, 0)
            currentItemHorizontalMargin = getDimensionPixelSize(R.styleable.CarouselView_currentItemMargin, 0)
        }

        setup()
    }

    private fun setup() {
        carouselViewPager.apply {
            offscreenPageLimit = 2

            val pageTranslationX = nextItemVisible + currentItemHorizontalMargin

            setPageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
            }

            addItemDecoration(
                PagerMarginItemDecoration(
                    currentItemHorizontalMargin
                )
            )
        }
    }

    fun enableAutoScroll(scope: CoroutineScope, intervalMs: Long = 3_000L) {
        carouselViewPager
            .getChildAt(0)
            .setOnTouchListener(ViewPager2AutoScroller(carouselViewPager, scope, intervalMs))
    }
}
