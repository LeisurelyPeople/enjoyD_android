package com.leisurely.people.enjoyd.ui.search.autoResult

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.data.remote.search.AutoResult
import com.leisurely.people.enjoyd.databinding.ItemAutoResultBinding
import com.leisurely.people.enjoyd.databinding.ItemAutoResultCategoryBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.item_auto_result.view.*

/**
 * 자동 검색완성 레이아웃에서 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
@Deprecated("기획적으로 확정이 난 내용이 아니어서 이 화면은 사용하지 않음")
class AutoResultAdapter :
    BaseListAdapter<AutoResult>(object : DiffUtil.ItemCallback<AutoResult>() {
        override fun areItemsTheSame(oldItem: AutoResult, newItem: AutoResult): Boolean {
            return oldItem == newItem // check uniqueness
        }

        override fun areContentsTheSame(oldItem: AutoResult, newItem: AutoResult): Boolean {
            return oldItem == newItem // check contents
        }
    }) {
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isCategory) {
            TYPE_AUTO_RESULT_CATEGORY
        } else {
            TYPE_AUTO_RESULT
        }
    }

    override fun onBindView(binding: ViewDataBinding, viewHolder: BaseItemVH, item: AutoResult) {
        if (viewHolder.itemViewType == TYPE_AUTO_RESULT_CATEGORY)
            binding.setVariable(BR.autoResultCategory, item)
        else
            binding.setVariable(BR.autoResult, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return if (viewType == TYPE_AUTO_RESULT_CATEGORY)
            ItemAutoResultCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {

            }
        else
            ItemAutoResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).apply {
                root.setOnClickListener {
                    val message = "${root.auto_result_title}"
                    Toast.makeText(parent.context, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    companion object {
        const val TYPE_AUTO_RESULT_CATEGORY = 0
        const val TYPE_AUTO_RESULT = 1
    }
}
