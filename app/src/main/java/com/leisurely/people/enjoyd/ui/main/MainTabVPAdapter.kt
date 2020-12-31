package com.leisurely.people.enjoyd.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 메인화면 BottomNavigationView Adapter
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class MainTabVPAdapter(fragmentManager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}