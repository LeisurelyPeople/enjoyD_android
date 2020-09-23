package com.leisurely.people.enjoyd.api

import com.leisurely.people.enjoyd.AndroidBaseTest
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Drama 에 관한 Api 테스트
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
class DramaRepositoryTest : AndroidBaseTest() {
    /** 간략한 드라마 정보 리스트 API 를 테스트한다. */
    @Test
    fun dramasTest() {
        val dramaInfoResponse = authApi.getDramas()
        dramaInfoResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 자세한 드라마 정보 리스트 API 를 테스트한다. */
    @Test
    fun dramasInfoSlugTest() {
        val dramaInfoSlugResponse = authApi.getDramasSlug("manjjijnamnyeo")
        dramaInfoSlugResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }

    /** 드라마 정보 검색 API 를 테스트한다. */
    @Test
    fun dramasSearchTest() {
        val dramaInfoSearchResponse = authApi.getDramasSearch(" ")
        dramaInfoSearchResponse.doOnSuccess {
            println("doOnSuccess : $it")
        }.test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                println("assertValue : $it")
                true
            }
            .assertComplete()
    }
}
