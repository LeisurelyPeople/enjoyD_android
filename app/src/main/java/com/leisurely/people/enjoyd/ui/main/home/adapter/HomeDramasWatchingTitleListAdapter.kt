package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.content.Context
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
import com.leisurely.people.enjoyd.databinding.ItemHomeDramasWatchingTitleBinding
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan

/**
 * 홈화면 시청중인 드라마 리스트 타이틀(텍스트) 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class HomeDramasWatchingTitleListAdapter :
    ListAdapter<Unit, HomeDramasWatchingTitleListAdapter.HomeDramasWatchingTitleVH>(object :
        DiffUtil.ItemCallback<Unit>() {
        override fun areItemsTheSame(oldItem: Unit, newItem: Unit): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Unit, newItem: Unit): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDramasWatchingTitleVH {
        return HomeDramasWatchingTitleVH(
            ItemHomeDramasWatchingTitleBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeDramasWatchingTitleVH, position: Int) {
        holder.bind()
    }

    inner class HomeDramasWatchingTitleVH(private val binding: ItemHomeDramasWatchingTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind() {
            binding.tvDramasWatchingTitle.text =
                getDramasWatchingTitleStringBuilder(binding.root.context)
        }

        private fun getDramasWatchingTitleStringBuilder(context: Context): SpannedString {
            val typefaceNotoSansBold =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_bold)
            val typefaceNotoSansMedium =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_medium)

            return buildSpannedString {
                append(SpannableString("시청중").apply {
                    setSpan(
                        CustomTypefaceSpan(typefaceNotoSansBold),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(
                            context.resources.getColor(
                                R.color.color_main_200,
                                context.theme
                            )
                        ),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
                append(SpannableString(" 웹드라마 이어보기").apply {
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