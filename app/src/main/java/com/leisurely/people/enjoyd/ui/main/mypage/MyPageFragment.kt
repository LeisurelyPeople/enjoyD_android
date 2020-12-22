package com.leisurely.people.enjoyd.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentMyPageBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.main.mypage.dibs.SavedDramasFragment
import com.leisurely.people.enjoyd.ui.main.mypage.inquire.InquireFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 마이페이지 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class MyPageFragment :
    BaseFragment<FragmentMyPageBinding, MyPageViewModel>(R.layout.fragment_my_page) {

    override val viewModel: MyPageViewModel by viewModel()

    private val myPageVP2Adapter: MyPageVP2Adapter by lazy {
        MyPageVP2Adapter(
            this,
            listOf(SavedDramasFragment.newInstance(), InquireFragment.newInstance())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager2()
        setMyPageTab()
        observeViewModelLiveData()
    }

    /** 뷰페이저2 셋팅 */
    private fun setViewPager2() {
        binding.vp2MyPage.run {
            adapter = myPageVP2Adapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    // viewpager scrolling 시 탭 활성화 처리
                    binding.myPageTabView.selectedPosition = position
                }
            })
        }
    }

    /** 커스텀뷰(마이페이지 텝 뷰) 셋팅 */
    private fun setMyPageTab() {
        binding.myPageTabView.tabSelectedListener =
            object : MyPageTabView.OnTabSelectedListener {
                override fun onTabSelected(position: Int) {
                    // 탭 클릭 시 viewpager page 이동 처리
                    binding.vp2MyPage.currentItem = position
                }
            }
    }

    private fun observeViewModelLiveData() {
        with(viewModel) {
            /** 사용자 수정 화면 이동 여부 observer */
            startProfileEditPage.observe(viewLifecycleOwner, Observer {
                // TODO 사용자 정보 수정화면으로 전환 (담당자 : Wayne)
            })
        }
    }

    companion object {
        fun newInstance(): MyPageFragment = MyPageFragment()
    }
}