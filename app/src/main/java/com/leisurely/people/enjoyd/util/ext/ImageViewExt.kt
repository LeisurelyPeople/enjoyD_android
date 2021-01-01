package com.leisurely.people.enjoyd.util.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.leisurely.people.enjoyd.R

/**
 * ImageView Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** Url imageView */
@BindingAdapter("imgUrlRound")
fun ImageView.loadRoundedImageUrl(url: String?) {
    val cornerRadius = context.resources.getDimensionPixelSize(R.dimen.detail_rel_other_radius)

    Glide.with(this)
        .load(url)
        .error(R.drawable.img_poster_empty)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(cornerRadius)))
        .transition(GenericTransitionOptions.with(R.anim.fragment_fade_enter))
        .into(this)

}

@BindingAdapter("imgUrl")
fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .transition(GenericTransitionOptions.with(R.anim.fragment_fade_enter))
            .into(this)
    }
}

/** Resource imageView */
@BindingAdapter("imgRes")
fun ImageView.setImageRes(resId: Int) {
    setImageResource(resId)
}