package com.leisurely.people.enjoyd.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter

/**
 * 요약 평가하기 화면에 있는 드라마 아이템 recyclerview
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.11
 */
class SummaryEvaluationRVAdapter(private val ratingBarChanged: (rating: Float, idx: Int) -> Unit) :
    BaseRVAdapter<DramaEvaluationResponse>() {

    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramaEvaluationResponse
    ) {
        (binding as ItemDramaEvaluationBinding).item = item

    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemDramaEvaluationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            rbsDramaGrade.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                item?.let {
                    ratingBarChanged.invoke(rating, it.pk)
                }
            }
        }
    }
}