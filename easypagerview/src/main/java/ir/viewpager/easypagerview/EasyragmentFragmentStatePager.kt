package ir.viewpager.easypagerview


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class EasyragmentFragmentStatePager constructor(fragmentmanager: FragmentManager, items: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(fragmentmanager) {


    override fun getItem(position: Int): Fragment {
      return mFragmentList[position]
    }


    override fun getCount(): Int {
      return  mFragmentList.size
    }

    private val mFragmentList: ArrayList<Fragment> = items

}