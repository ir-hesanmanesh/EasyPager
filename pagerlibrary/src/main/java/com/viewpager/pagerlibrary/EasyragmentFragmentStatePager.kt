package com.viewpager.pagerlibrary


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class EasyragmentFragmentStatePager constructor(fragmentmanager: FragmentManager, items: Fragment) :
    FragmentStatePagerAdapter(fragmentmanager) {


    override fun getItem(position: Int): Fragment {
      return mFragmentList[position]
    }


    override fun getCount(): Int {
      return  mFragmentList.size
    }

    private val mFragmentList: ArrayList<Fragment> = arrayListOf(items)

}