package com.leisurely.people.enjoyd.ui.evaluation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.databinding.ItemEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.item_evaluation.view.*

/**
 * 평가 화면에서 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v11.4.0 / 2020.07.01
 */
class EvaluationListAdapter : BaseListAdapter<String>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem // check uniqueness
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem // check contents
    }
}) {
    override fun onBindView(binding: ViewDataBinding, viewHolder: BaseItemVH, item: String) {
        binding.setVariable(BR.data, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val binding =
            ItemEvaluationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return binding.apply {
            root.evaluation_rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                ratingBar.rating = rating
            }
        }
    }
}
