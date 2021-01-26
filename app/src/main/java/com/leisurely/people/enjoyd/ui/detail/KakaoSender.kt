package com.leisurely.people.enjoyd.ui.detail

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.*
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.network.storage.ImageUploadResponse
import com.leisurely.people.enjoyd.model.detail.ShareDrama
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.Constant.Companion.KAKAO_LINK_SLUG
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * 카카오 메시지 전송 클래스
 * TODO 추후 다른 곳에서도 활용할 시 파일의 위치를 옮긴다.
 *
 * @author ricky
 * @since v1.0.0 / 2021.01.12
 */

/**
 * 카카오톡으로 전송할 피드 템플릿 [FeedTemplate] 을 만든다.
 *
 * @param sharedDrama 피드 템플릿에 들어갈 드라마 데이터 정보
 */
fun generateMessage(sharedDrama: ShareDrama): TemplateParams = FeedTemplate
    .newBuilder(
        ContentObject.newBuilder(
            sharedDrama.title,
            sharedDrama.poster,
            LinkObject.newBuilder().build()
        ).setDescrption("감독 : ${sharedDrama.writer}, 평점 : ${sharedDrama.avgRating}").build()
    ).addButton(
        ButtonObject(
            "앱에서 보기",
            LinkObject.newBuilder()
                .setAndroidExecutionParams("$KAKAO_LINK_SLUG=${sharedDrama.slug}")
                .setIosExecutionParams("$KAKAO_LINK_SLUG=${sharedDrama.slug}")
                .build()
        )
    ).build()

/**
 * 카카오톡으로 만든 피드 템플릿 [TemplateParams] 을 전송한다.
 *
 * @param params [generateMessage] 을 통해 완성한피드 템플릿
 */
suspend fun <B : ViewDataBinding, VM : BaseViewModel> BaseActivity<B, VM>.sendMessage(
    params: TemplateParams
) = suspendCoroutine<URL?> {

    // 기본 템플릿으로 카카오링크 보내기
    KakaoLinkService.getInstance().sendDefault(this, params, object :
        ResponseCallback<KakaoLinkResponse>() {
        override fun onFailure(errorResult: ErrorResult) {
            Log.e("KAKAO_API", "카카오링크 공유 실패: $errorResult")
        }

        override fun onSuccess(result: KakaoLinkResponse) {
            Log.i("KAKAO_API", "카카오링크 공유 성공")

            // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
            Log.w("KAKAO_API", "warning messages: " + result.warningMsg)
            Log.w("KAKAO_API", "argument messages: " + result.argumentMsg)
        }
    })
}

/**
 * 카카오에 이미지를 업로드한다.
 *
 * @param imageUrl 업로드할 poster 이미지
 */
suspend fun <B : ViewDataBinding, VM : BaseViewModel> BaseActivity<B, VM>.uploadImage(
    imageUrl: String
) = suspendCoroutine<String?> {

    // 카카오 이미지 서버로 스크랩
    KakaoLinkService.getInstance().scrapImage(this, true, imageUrl,
        object : ResponseCallback<ImageUploadResponse>() {
            override fun onFailure(errorResult: ErrorResult) {
                Log.e("KAKAO_API", "이미지 업로드 실패: $errorResult")
                it.resume(null)
            }

            override fun onSuccess(result: ImageUploadResponse) {
                Log.i("KAKAO_API", "이미지 업로드 성공")
                Log.d("KAKAO_API", "URL: ${result.original.url}")

                it.resume(result.original.url)
            }
        })
}
