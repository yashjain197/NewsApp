package com.yashjain.newsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class viewPagerAdapter(supportFragmentManager:FragmentManager) :FragmentPagerAdapter(supportFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val FragmentList = ArrayList<Fragment>()
    private val FragmenTitle = ArrayList<String>()
    override fun getCount(): Int {
        return FragmentList.size;
    }

    override fun getItem(position: Int): Fragment {
        return FragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return FragmenTitle[position]
    }

    fun addFragment(fragment: Fragment,title:String){
        FragmentList.add(fragment)
        FragmenTitle.add(title)
    }
}