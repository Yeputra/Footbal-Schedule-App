package com.freaky.id.footballscheduleapp.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabTeamAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> FragmentAllTeam.newInstance()
        1 -> FragmentNext.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Teams"
        1 -> "Favorite"
        else -> ""
    }

    override fun getCount(): Int = 2
}