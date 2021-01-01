package com.leisurely.people.enjoyd.ui.main.evaluation.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ItemEvaluationHeaderTitleBinding
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan

/**
 * 평가하기 화면 상단 타이틀 UI를 보여줄 RecyclerView Adapter
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.14
 */
class EvaluationHeaderTextRVAdapter :
    RecyclerView.Adapter<EvaluationHeaderTextRVAdapter.EvaluationHeaderTextVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluationHeaderTextVH {
        return EvaluationHeaderTextVH(
            ItemEvaluationHeaderTitleBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: EvaluationHeaderTextVH, position: Int) {
        holder.bind()
    }

    inner class EvaluationHeaderTextVH(private val binding: ItemEvaluationHeaderTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.tvTitle.text = getScreenTitleStringBuilder()
        }

        private fun getScreenTitleStringBuilder(): SpannedString {
            with(binding.root.context) {
                val typefaceNotoSansBold =
                    ResourcesCompat.getFont(this, R.font.noto_sans_kr_bold)
                val typefaceNotoSansDemiLight =
                    ResourcesCompat.getFont(this, R.font.noto_sans_kr_demi_light)

                return buildSpannedString {
                    append(SpannableString("감상한 웹드라마").apply {
                        setSpan(
                            CustomTypefaceSpan(typefaceNotoSansBold),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.color_main_100, theme)),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    })
                    append(SpannableString(" 작품을\n").apply {
                        setSpan(
                            CustomTypefaceSpan(typefaceNotoSansDemiLight),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    })
                    append(SpannableString("평가").apply {
                        setSpan(
                            CustomTypefaceSpan(typefaceNotoSansBold),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    })
                    append(SpannableString("해주세요!").apply {
                        setSpan(
                            CustomTypefaceSpan(typefaceNotoSansDemiLight),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        setSpan(
                            ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                            0,
                            this.length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    })
                }
            }
        }
    }
}