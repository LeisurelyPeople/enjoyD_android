package com.leisurely.people.enjoyd.data.remote.data.response

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.leisurely.people.enjoyd.util.ext.styledNumber
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 드라마정보 디테일 API (/dramas/{drama_info_slug}) 내
 * [DramasSlugGetResponse.dramas] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramasSlugResponseItem(
    val pk: String,

    @SerialName("video_id")
    val videoId: String,

    val title: String,

    val episode: Int,

    val summary: String,

    val duration: Int,

    @SerialName("small_thumbnail")
    val smallThumbnail: String
)

@BindingAdapter("detailOtherTitle", "detailOtherEpisode")
fun TextView.getDetailOtherTitle(
    title: String,
    episode: Int
) {
    text = "$title EP ${episode.styledNumber()}"
}