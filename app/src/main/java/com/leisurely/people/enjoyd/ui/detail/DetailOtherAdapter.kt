package com.leisurely.people.enjoyd.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugResponseItem
import com.leisurely.people.enjoyd.databinding.ItemOtherBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter
import com.leisurely.people.enjoyd.ui.video.VideoActivity
import com.leisurely.people.enjoyd.util.ext.setOnSingleClickListener

/**
 * 다른 회차 둘러보기 adapter
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class DetailOtherAdapter : BaseRVAdapter<DramasSlugResponseItem>() {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSlugResponseItem
    ) {
        (binding as ItemOtherBinding).item = item
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemOtherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            this.root.setOnSingleClickListener {
                val intent = Intent(parent.context, VideoActivity::class.java).apply {
                    putExtra("video_id", item?.videoId ?: "")
                }
                parent.context.startActivity(intent)
            }
        }
    }

}