package com.leisurely.people.enjoyd.ui.video

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityVideoBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_VIDEO_ID
import com.leisurely.people.enjoyd.util.time.seconds
import kotlinx.android.synthetic.main.activity_video.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 웹 드라마 재생 화면
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>(
    R.layout.activity_video
) {
    val tag = VideoActivity::class.java.canonicalName ?: "VideoActivity"

    override val viewModel: VideoViewModel by viewModel()

    var simpleExoPlayer: SimpleExoPlayer? = null
    private val playerListener = object : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            super.onPlayerStateChanged(playWhenReady, playbackState)
            when (playbackState) {
                //재생 실패
                Player.STATE_IDLE -> {
                }

                // 재생 준비
                Player.STATE_BUFFERING -> {
                }

                // 재생 준비 완료
                Player.STATE_READY -> {
                }

                // 재생 마침
                Player.STATE_ENDED -> {
                }

                else -> {
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            val videoId = intent.getStringExtra(EXTRA_VIDEO_ID) ?: ""
            val youtubeLink = "http://youtube.com/watch?v=$videoId"

            YoutubeExtractor(this) { initPlayer(it) }
                .extract(youtubeLink, true, true)
        }
    }

    override fun onResume() {
        super.onResume()
        ypv_drama?.player?.playWhenReady = true

    }

    override fun onPause() {
        super.onPause()
        ypv_drama?.player?.playWhenReady = false

    }

    override fun onDestroy() {
        super.onDestroy()
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            releasePlayer()
        }
    }

    /** 처음 화면에 들어왔을 때 exoPlayer 를 init 해주는 작업을 수행한다. onCreate 단계에서만 수행한다. */
    private fun initPlayer(playUrl: String) {
        // val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        // 미디어 소스 초기화
        val extractorFactory = ExtractorMediaSource.Factory(
            DefaultDataSourceFactory(
                this,
                Util.getUserAgent(this, this.applicationInfo.packageName)
            )
        )
        val parsedUrl = Uri.parse(playUrl)
        val mediaSource = extractorFactory.createMediaSource(parsedUrl)
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        // 플레이어 연결
        ypv_drama.player = simpleExoPlayer

        // 준비한 후 재생 시작
        simpleExoPlayer?.prepare(mediaSource)
        ypv_drama?.player?.playWhenReady = true

        // 특정 지점으로 이동
        ypv_drama.player.seekTo(0)

        simpleExoPlayer?.addListener(playerListener)
    }

    /** 화면을 종료할 때 exoPlayer 를 해제해주는 작업을 수행한다. onDestroy 단계에서만 수행한다. */
    private fun releasePlayer() {
        if (simpleExoPlayer != null) {
            ypv_drama.player = null
            simpleExoPlayer?.removeListener(playerListener)
            simpleExoPlayer?.release()
            simpleExoPlayer = null
        }
    }
}
