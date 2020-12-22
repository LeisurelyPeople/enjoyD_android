package com.leisurely.people.enjoyd.ui.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentMyPageBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelLiveData()
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