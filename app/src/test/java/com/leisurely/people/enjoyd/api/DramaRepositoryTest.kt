package com.leisurely.people.enjoyd.api

import com.leisurely.people.enjoyd.AndroidBaseTest
import com.leisurely.people.enjoyd.data.remote.data.request.DeleteAccountsDramasDramaBookmarksRequest
import org.junit.Test
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

/**
 * Drama 에 관한 Api 테스트
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
class DramaRepositoryTest : AndroidBaseTest() {
    /** 드라마 정보 배너 API [getDramasInfoBanner] 를 테스트한다. */
    @Test
    fun dramasInfoBannerTest() {
        val dramaInfoBannerResponse = authApi.getDramasBanner()
        dramaInfoBannerResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 드라마정보리스트API API [getDramas] 를 테스트한다. */
    @Test
    fun dramasTest() {
        val dramaInfoResponse = authApi.getDramas()
        dramaInfoResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 드라마정보 디테일 API [getDramasSlug] 를 테스트한다 */
    @Test
    fun dramasInfoSlugTest() {
        val dramaInfoSlugResponse = authApi.getDramasSlug("manjjijnamnyeo")
        dramaInfoSlugResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 해당 드라마의 에피소드 목록 API [getDramasSlugEpisodes] 를 테스트한다 */
    @Test
    fun dramasInfoEpisodesTest() {
        val dramasSlugEpisodesResponse = authApi.getDramasSlugEpisodes("manjjijnamnyeo")
        dramasSlugEpisodesResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 해당 드라마의 연관 드라마 목록 API [getDramasSlugRelatedSearch] 를 테스트한다 */
    @Test
    fun dramasSlugRelatedSearchTest() {
        val dramasSlugRelatedSearchResponse = authApi.getDramasSlugRelatedSearch("manjjijnamnyeo")
        dramasSlugRelatedSearchResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 드라마 정보 검색 API [getDramasSearch] 를 테스트한다. */
    @Test
    fun dramasSearchTest() {
        val dramaInfoSearchResponse = authApi.getDramasSearch(" ")
        dramaInfoSearchResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 태그 리스트 API [getDramasTag] 를 테스트한다. */
    @Test
    fun dramasTagTest() {
        val dramaTagResponse = authApi.getDramasTag()
        dramaTagResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertError { error ->
                print("$error")
                error is HttpException && error.code() == 404
                true
            }
        // TODO 에러가 나지 않는 케이스에서 사용할 것
        // .assertValue {
        //     println("assertValue : $it")
        //     true
        // }
        // .assertComplete()
    }

    /** 북마크 리스트 API [getAccountsDramasDramaBookmarks] 를 테스트한다. */
    @Test
    fun dramasBookmarkPkTest() {
        val dramaBookmarkPkResponse = authApi.getAccountsDramasDramaBookmarks()
        dramaBookmarkPkResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 북마크 다중 해제 API [deleteAccountsDramasDramaBookmarks] 를 테스트한다. */
    @Test
    fun dramasBookmarkDeleteTest() {
        val deleteDramasBookmarkResponse =
            authApi.deleteAccountsDramasDramaBookmarks(
                data = DeleteAccountsDramasDramaBookmarksRequest(
                    arrayListOf(3)
                )
            )
        deleteDramasBookmarkResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
    }

    /**
     * 북마크 API [postAccountsDramasDramaPkBookmark]
     * 북마크 해제 API [deleteAccountsDramasDramaPkBookmark] 를 테스트한다.
     */
    @Test
    fun dramasBookmarkPostDeleteTest() {
        // 북마크 API [postAccountsDramasDramaPkBookmark]
        val postDramasBookmarkResponse =
            authApi.postAccountsDramasDramaPkBookmark(3)
        postDramasBookmarkResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)

        // 북마크 해제 API [deleteAccountsDramasDramaPkBookmark]
        val deleteDramasBookmarkResponse =
            authApi.deleteAccountsDramasDramaPkBookmark(dramaPk = 3)
        deleteDramasBookmarkResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.doOnError { error ->
            println("doOnError : $error")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
    }
}
