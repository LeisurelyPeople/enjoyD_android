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
//    @Ignore("서버 API 호출 최소화")
    @Test
    fun dramasInfoTest() {
        val dramaInfoResponse = noneAuthApi.getDramasInfo()
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
//    @Ignore("아직 구현되지 않은듯함 (404 뜸)")
    @Test
    fun dramasInfoPkTest() {
        val dramaInfoPkResponse = noneAuthApi.getDramasInfoPk("ee")
        dramaInfoPkResponse.doOnSuccess {
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
    // @Ignore("서버 API 호출 최소화")
    @Test
    fun dramasInfoSearchTest() {
        val dramaInfoSearchResponse = noneAuthApi.getDramasInfoSearch("abcd")
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
