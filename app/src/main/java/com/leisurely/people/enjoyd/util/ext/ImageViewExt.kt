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
@BindingAdapter(value = ["imgUrl", "imgRadius"], requireAll = false)
fun ImageView.bindImageUrl(url: String?, radius: Float = 0F) {
    url?.let {
        if (radius == 0F) {
            Glide.with(this)
                .load(it)
                .transition(GenericTransitionOptions.with(R.anim.fragment_fade_enter))
                .into(this)
        } else {
            Glide.with(this)
                .load(it)
                .apply(RequestOptions().transform(RoundedCorners(radius.toInt())))
                .transition(GenericTransitionOptions.with(R.anim.fragment_fade_enter))
                .into(this)
        }
    }
}

/** Resource imageView */
@BindingAdapter("imgRes")
fun ImageView.setImageRes(resId: Int) {
    setImageResource(resId)
}