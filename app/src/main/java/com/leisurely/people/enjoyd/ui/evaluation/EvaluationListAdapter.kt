package com.leisurely.people.enjoyd.ui.evaluation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.databinding.ItemDramaEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter

/**
 * 평가 화면에서 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
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
//        binding.setVariable(BR.data, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemDramaEvaluationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }
}
