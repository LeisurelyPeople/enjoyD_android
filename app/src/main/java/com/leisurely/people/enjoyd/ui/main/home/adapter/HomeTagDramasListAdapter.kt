package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.content.Context
import android.graphics.Rect
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.databinding.ItemHomeTagDramasBinding
import com.leisurely.people.enjoyd.model.ResultWrapperModel
import com.leisurely.people.enjoyd.ui.base.adapter.OnRecyclerViewItemClick
import com.leisurely.people.enjoyd.ui.common.adapter.DramaListAdapter
import com.leisurely.people.enjoyd.util.CustomItemDecoration
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan

/**
 * 홈 화면안에 태그를 통해 검색한 드라마 RecyclerView Adapter 클래스
 * @author Wayne
 * @since v1.0.0 / 2020.09.29
 */
class HomeTagDramasListAdapter(
    private val onItemClick: OnRecyclerViewItemClick<DramasItemResponse>
) : ListAdapter<ResultWrapperModel<List<DramasItemResponse>>,
        HomeTagDramasListAdapter.HomeTagDramasVH>(
    object : DiffUtil.ItemCallback<ResultWrapperModel<List<DramasItemResponse>>>() {
        override fun areItemsTheSame(
            oldItem: ResultWrapperModel<List<DramasItemResponse>>,
            newItem: ResultWrapperModel<List<DramasItemResponse>>
        ): Boolean = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: ResultWrapperModel<List<DramasItemResponse>>,
            newItem: ResultWrapperModel<List<DramasItemResponse>>
        ): Boolean = oldItem == newItem
    }) {

    private val tagDramasTagsRecycledViewPool = RecyclerView.RecycledViewPool()

    var tag: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTagDramasVH {
        return HomeTagDramasVH(
            ItemHomeTagDramasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            setHomeTagDramaChildRV()
        }
    }

    override fun onBindViewHolder(holder: HomeTagDramasVH, position: Int) {
        holder.bind()
    }

    inner class HomeTagDramasVH(private val binding: ItemHomeTagDramasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setHomeTagDramaChildRV() {
            binding.rvDramas.run {
                adapter = DramaListAdapter {
                    onItemClick(it)
                }
                isNestedScrollingEnabled = false
                setRecycledViewPool(tagDramasTagsRecycledViewPool)
                addItemDecoration(object : CustomItemDecoration() {
                    override fun setSpacingForDirection(
                        outRect: Rect,
                        layoutManager: RecyclerView.LayoutManager?,
                        position: Int,
                        itemCount: Int
                    ) {
                        val column = position % 2
                        val spacing =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)

                        outRect.left = column * spacing / 2
                        outRect.right = spacing - (column + 1) * spacing / 2
                        outRect.bottom =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                    }
                })
            }
        }

        fun bind() {
            binding.tvTitle.text = getTagDramasTitle(binding.root.context)
            binding.items = currentList.singleOrNull()?.data ?: mutableListOf()
        }

        /** 해당 UI의 타이틀 값을 가져오기 위한 메소드 */
        private fun getTagDramasTitle(context: Context): SpannedString {
            val typefaceNotoSansBold =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_bold)
            val typefaceNotoSansMedium =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_medium)

            return buildSpannedString {
                append(SpannableString(tag).apply {
                    setSpan(
                        CustomTypefaceSpan(typefaceNotoSansBold),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(
                            context.resources.getColor(
                                R.color.color_main_100,
                                context.theme
                            )
                        ),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
                append(SpannableString(" 웹드라마").apply {
                    setSpan(
                        CustomTypefaceSpan(typefaceNotoSansMedium),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(
                            context.resources.getColor(
                                R.color.color_grey_0,
                                context.theme
                            )
                        ),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
            }
        }
    }
}
