package com.corro.gothouses.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.corro.gothouses.R

class MarginDividerItemDecoration(context: Context, leftMarginDp: Int = 0, rightMarginDp: Int = 0) :
    RecyclerView.ItemDecoration() {

    private val divider = ContextCompat.getDrawable(context, R.drawable.custom_divider)
    private val leftMarginPx = dpToPx(context, leftMarginDp)
    private val rightMarginPx = dpToPx(context, rightMarginDp)
    var setForLastItem: Boolean = true

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val rightMargin = parent.width - rightMarginPx

        for (i in 0 until parent.childCount) {
            if (setForLastItem || i != parent.childCount - 1) {
                val child = parent.getChildAt(i)
                val childParams = child.layoutParams as RecyclerView.LayoutParams
                val dividerTop = child.bottom + childParams.bottomMargin
                val dividerBottom = dividerTop + divider!!.intrinsicHeight
                divider.setBounds(leftMarginPx, dividerTop, rightMargin, dividerBottom)
                divider.draw(c)
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, divider!!.intrinsicHeight)
    }

    private fun dpToPx(context: Context, marginInDp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            marginInDp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}