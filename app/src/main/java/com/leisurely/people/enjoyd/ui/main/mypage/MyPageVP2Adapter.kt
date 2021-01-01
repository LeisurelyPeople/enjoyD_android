package com.leisurely.people.enjoyd.ui.main.mypage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * 마이페이지 ViewPager2 어댑터 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class MyPageVP2Adapter(fragment: Fragment, private val lists: List<Fragment>) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = lists.size
    override fun createFragment(position: Int): Fragment = lists[position]
}