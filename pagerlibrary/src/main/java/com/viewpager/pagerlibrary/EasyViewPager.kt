package com.viewpager.pagerlibrary

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class EasyViewPager constructor(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private var colors: IntArray? = intArrayOf()
    private var mAdapter: EasyPageAdapter? = null

    val backGroundColors: Int
        get() = if (colors == null) 0 else colors!!.size

    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (mAdapter != null) {
                val nextColor = colors!![(mAdapter!!.getIndex(position) + 1) % colors!!.size]
                val currentColor = colors!![mAdapter!!.getIndex(position) % colors!!.size]
                val currentRed = (currentColor shr 16 and 0xff).toFloat()
                val currentGreen = (currentColor shr 8 and 0xff).toFloat()
                val currentBlue = (currentColor and 0xff).toFloat()
                val nextRed = (nextColor shr 16 and 0xff).toFloat()
                val nextGreen = (nextColor shr 8 and 0xff).toFloat()
                val nextBlue = (nextColor and 0xff).toFloat()

                val newRed = (currentRed + (nextRed - currentRed) * positionOffset).toInt()
                val newGreen = (currentGreen + (nextGreen - currentGreen) * positionOffset).toInt()
                val newBlue = (currentBlue + (nextBlue - currentBlue) * positionOffset).toInt()
                setBackgroundColor(Color.rgb(newRed, newGreen, newBlue))
            }
        }

        override fun onPageSelected(position: Int) {}

        override fun onPageScrollStateChanged(state: Int) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                val position = currentItem
                if (position == 0) {
                    setCurrentItem(colors!!.size, false)
                } else if (position == adapter!!.count - 1) {
                    setCurrentItem(1, false)
                }
            }
        }
    }

    init {
        addOnPageChangeListener(mOnPageChangeListener)
    }

    fun setBackGroundColors(colors: IntArray) {
        this.colors = colors
        setBackgroundColor(colors[colors.size - 1])
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        mAdapter = EasyPageAdapter(adapter)
        super.setAdapter(mAdapter)
        currentItem = 1
    }
}
