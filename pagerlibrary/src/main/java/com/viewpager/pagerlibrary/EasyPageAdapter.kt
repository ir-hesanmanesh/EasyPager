package com.viewpager.pagerlibrary

import android.database.DataSetObserver
import android.os.Parcelable
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

 class EasyPageAdapter constructor(private val adapter: PagerAdapter?) : PagerAdapter() {



     override fun getCount(): Int {
        return adapter!!.count + 2
    }

    private fun getOriginCount(): Int {
        return adapter!!.count
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return adapter!!.instantiateItem(container, getIndex(position))
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(container)
    }

    override fun getPageWidth(position: Int): Float {
        return adapter!!.getPageWidth(getIndex(position))
    }

    fun getIndex(position: Int): Int {
        return when (position) {
            0 -> getOriginCount() - 1
            count - 1 -> 0
            else -> position - 1
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return adapter!!.getPageTitle(getIndex(position))
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun equals(other: Any?): Boolean {
        return adapter == other
    }

    override fun hashCode(): Int {
        return adapter.hashCode()
    }

    override fun toString(): String {
        return adapter.toString()
    }

    override fun getItemPosition(`object`: Any): Int {
        return adapter!!.getItemPosition(`object`)
    }

    override fun saveState(): Parcelable? {
        return adapter!!.saveState()
    }

    override fun finishUpdate(container: ViewGroup) {
        return adapter!!.finishUpdate(container)
    }

    override fun notifyDataSetChanged() {
        adapter!!.notifyDataSetChanged()
    }

    override fun registerDataSetObserver(observer: DataSetObserver) {
        return adapter!!.registerDataSetObserver(observer)
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        return adapter!!.restoreState(state, loader)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        return adapter!!.setPrimaryItem(container, getIndex(position), `object`)
    }

    override fun startUpdate(container: ViewGroup) {
        return adapter!!.startUpdate(container)
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {
        return adapter!!.unregisterDataSetObserver(observer)
    }



}
