package ir.libpager.easypagerview

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import androidx.viewpager.widget.ViewPager


class EasyViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) :
    ViewPager(context, attrs) {

    /**
     * ViewPager Mode
     * DEFAULT, SLIDESHOW
     */
    private var mode: Mode = Mode.DEFAULT

    /**
     * ViewPager can scroll or not
     */
    private var scroll: Boolean = DEFAULT_SCROLL
    /**
     * ViewPager Page Change Duration
     */
    private var duration: Int = DEFAULT_DURATION

    /**
     * Slider Handler
     */
    private var slideHandler: Handler? = null

    /**
     * Pager Slider
     */
    private val pagerSlider = object : Runnable {
        override fun run() {
            if (isLastItems()) {
                currentItem = 0
            } else {
                currentItem += 1
            }
            slideHandler?.postDelayed(this, duration.toLong())
        }

    }
    /**
     * ViewPageR scroll speed
     */
    var velocity: Int = DEFAULT_VELOCITY
        set(value) {
            field = value
            initVelocity()
        }

    private fun initVelocity() {
        if (velocity > 0)
            addCustomingSpeedScroll()
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean = scroll && super.onTouchEvent(ev)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = scroll && super.onInterceptTouchEvent(ev)
    fun enableScroll() {
        scroll = true
    }

    fun disableScroll() {
        scroll = false
    }

    fun startSliding(duration: Int) {
        checkPagerSliding()
        if (duration <= MIN_DURATION) {
            throw IllegalArgumentException("Duration cannot be less than min duration!")
        }
    }

    fun startSliding() {
        checkPagerSliding()
        slide()
    }

    fun stopSliding() {
        if (mode != Mode.SLIDESHOW) {
            throw IllegalStateException("ViewPager is not sliding..")
        }
        mode = Mode.DEFAULT
        slideHandler?.removeCallbacks(pagerSlider)
        slideHandler = null
    }

     fun setAdapter(adapter: EasyragmentFragmentStatePager) {

        super.setAdapter(adapter)
    }

    /**
     * Calls the specified function [block] with `this` value as its argument and returns `this` value.
     **/
    private fun slide() {
        mode = Mode.SLIDESHOW
        slideHandler = Handler().also { it.postDelayed(pagerSlider, duration.toLong()) }
    }

    private fun checkPagerSliding() {
        if (mode == Mode.SLIDESHOW) {
            throw IllegalStateException("ViewPager is already sliding..")
        }
        if (adapter == null || adapter?.count == 0) {
            throw IllegalArgumentException("Nothing to slide!")
        }
    }

    private fun addCustomingSpeedScroll() {
        try {
            ViewPager::class.java.getDeclaredField(SCROLLER_FIELD_NAME)?.apply {
                isAccessible = true

            }.also {
                it?.set(this, CustomingSpeedScroll(context, LinearInterpolator(), velocity))
            }

        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }


    private fun isLastItems() = currentItem == adapter?.count!! - 1

    enum class Mode { DEFAULT, SLIDESHOW }

    init {
        if (attrs != null) {
            val typeArray = context.obtainStyledAttributes(attrs,
                R.styleable.EasyViewPager, defStyle, defStyleRes)
            try {
                typeArray.let {
                    scroll = it.getBoolean(
                        R.styleable.EasyViewPager_Scroll,
                        DEFAULT_SCROLL
                    )
                    duration = it.getInt(
                        R.styleable.EasyViewPager_duration,
                        DEFAULT_DURATION
                    )
                    velocity = it.getInt(
                        R.styleable.EasyViewPager_velocity,
                        DEFAULT_VELOCITY
                    )

                }
            } finally {
                typeArray.recycle()
            }
        }
    }

    companion object {
        private const val DEFAULT_DURATION = 5000
        private const val DEFAULT_SCROLL = true
        private const val DEFAULT_VELOCITY = 0
        private const val MIN_DURATION = 100
        private const val SCROLLER_FIELD_NAME = "mScroller"

    }
}