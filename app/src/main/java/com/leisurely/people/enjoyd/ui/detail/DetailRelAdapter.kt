package com.leisurely.people.enjoyd.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugRelatedSearchResponseItem
import com.leisurely.people.enjoyd.databinding.ItemRelBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_VIDEO_ID
import com.leisurely.people.enjoyd.util.ext.setOnSingleClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

/**
 * 연관 드라마 adapter
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class DetailRelAdapter : BaseRVAdapter<DramasSlugRelatedSearchResponseItem>() {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSlugRelatedSearchResponseItem
    ) {
        (binding as ItemRelBinding).item = item
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemRelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            this.root.setOnSingleClickListener {
                val intent = Intent(parent.context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_VIDEO_ID, item?.slug ?: "")
                }
                parent.context.startActivity(intent)
            }

//            this.relBookmark.setOnSingleClickListener {
//                val prevEnabled = this.relBookmark.isEnabled
//                this.relBookmark.isEnabled = !prevEnabled
//
//                // TODO 연관드라마인 경우 북마크 설정을 어떻게 해주는지 확인 필요
//                onItemClick(!prevEnabled, item?.slug ?: "", "0") {
//                    this.relBookmark.isEnabled = prevEnabled
//                }
//            }
        }
    }
}