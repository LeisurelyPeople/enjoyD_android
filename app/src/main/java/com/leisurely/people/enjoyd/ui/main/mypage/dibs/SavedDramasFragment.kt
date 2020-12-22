package com.leisurely.people.enjoyd.ui.main.mypage.dibs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentSavedDramasBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment

/**
 * 찜한 드라마 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class SavedDramasFragment :
    BaseFragment<FragmentSavedDramasBinding, SavedDramasViewModel>(R.layout.fragment_saved_dramas) {

    override val viewModel: SavedDramasViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): Fragment = SavedDramasFragment()
    }
}