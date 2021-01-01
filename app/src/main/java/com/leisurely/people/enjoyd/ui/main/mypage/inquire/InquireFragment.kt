package com.leisurely.people.enjoyd.ui.main.mypage.inquire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentInquireBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 문의/제안하기 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class InquireFragment :
    BaseFragment<FragmentInquireBinding, InquireViewModel>(R.layout.fragment_inquire) {

    override val viewModel: InquireViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): Fragment = InquireFragment()
    }
}