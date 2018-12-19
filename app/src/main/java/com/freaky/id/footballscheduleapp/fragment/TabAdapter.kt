package com.freaky.id.footballscheduleapp.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> FragmentLast.newInstance()
        1 -> FragmentNext.newInstance()
        2 -> FragmentFavorite.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Last Match"
        1 -> "Next Match"
        2 -> "Favorite Match"
        else -> ""
    }

    override fun getCount(): Int = 3
}
