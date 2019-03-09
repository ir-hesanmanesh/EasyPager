package ir.sample.easypagerview

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

class CustomingSpeedScroll constructor(
    context: Context, inderpolator: Interpolator,
    private val velocity: Int
) :
    Scroller(context, inderpolator) {
    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, velocity)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, velocity)
    }
}