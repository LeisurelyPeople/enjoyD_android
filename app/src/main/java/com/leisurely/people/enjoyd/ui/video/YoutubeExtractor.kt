package com.leisurely.people.enjoyd.ui.video

import android.content.Context
import android.util.Log
import android.util.SparseArray
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile

/**
 * StaticFieldLeak 을 피하기 위해 만든 커스텀 youtube Extractor
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.21
 */
class YoutubeExtractor(
    val context: Context, val action: (String) -> Unit
) : YouTubeExtractor(context) {
    val tag = this::class.java.canonicalName
    override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {
        if (ytFiles != null) {
            val itag = 22
            val downloadUrl = ytFiles[itag].url
            Log.i(
                tag, """extractYoutubeUrl() param
                           videoMeta: ${videoMeta?.title}
                           getUrl: $downloadUrl
                           SparseArraySize:${ytFiles.size()}
                        """.trimIndent()
            )

            action(downloadUrl)
        }
    }
}
