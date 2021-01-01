package com.leisurely.people.enjoyd.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugEpisodesResponseItem
import com.leisurely.people.enjoyd.databinding.ItemOtherBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter
import com.leisurely.people.enjoyd.ui.video.VideoActivity
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_VIDEO_ID
import com.leisurely.people.enjoyd.util.ext.setOnSingleClickListener
import com.leisurely.people.enjoyd.util.ext.styledNumber

/**
 * 다른 회차 둘러보기 adapter
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class DetailOtherAdapter(
    val onItemClick: (Boolean, String, String, () -> Unit) -> Unit
) : BaseRVAdapter<DramasSlugEpisodesResponseItem>() {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSlugEpisodesResponseItem
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
                    putExtra(EXTRA_VIDEO_ID, item?.videoId ?: "")
                }
                parent.context.startActivity(intent)
            }

            this.otherBookmark.setOnSingleClickListener {
                item?.let {
                    val prevEnabled = it.isBookmark
                    val prevItem = it.copy()
                    val newItem = it.copy(isBookmark = !prevEnabled)

                    replace(prevItem, newItem)

                    onItemClick(!prevEnabled, it.slug, "${it.episode}") {
                        replace(newItem, prevItem)
                    }
                }
            }
        }
    }
}

@BindingAdapter("detailOtherEpisode", "detailOtherTitle")
fun TextView.setDetailOtherTitle(episode: Int, title: String) {
    text = "$title EP ${episode.styledNumber()}"
}

@BindingAdapter("detailOtherSelected")
fun AppCompatImageView.setDetailOtherSelected(selected: Boolean) {
    this.isSelected = selected
}