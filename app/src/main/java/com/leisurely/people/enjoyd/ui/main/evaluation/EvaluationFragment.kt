package com.leisurely.people.enjoyd.ui.main.evaluation

import android.os.Bundle
import android.view.View
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 평가하기 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class EvaluationFragment :
    BaseFragment<FragmentEvaluationBinding, EvaluationViewModel>(R.layout.fragment_evaluation) {

    override val viewModel: EvaluationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): EvaluationFragment = EvaluationFragment()
    }
}