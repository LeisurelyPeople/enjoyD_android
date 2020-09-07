package com.leisurely.people.enjoyd.data

import com.leisurely.people.enjoyd.AndroidBaseTest
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoPkResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoSearchResponseItem
import com.leisurely.people.enjoyd.util.Serializer.asJson
import com.leisurely.people.enjoyd.util.Serializer.asJsonArray
import com.leisurely.people.enjoyd.util.Serializer.parse
import com.leisurely.people.enjoyd.util.Serializer.parseArray
import kotlinx.serialization.builtins.list
import org.junit.Assert
import org.junit.Test

/**
 * Drama 관련 데이터 파싱을 테스트한다.
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
class DramaDataTest : AndroidBaseTest() {
    /** 자세한 드라마 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasInfoTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """{
            "count": 2,
            "next": "다음 풀 도메인 주소",
            "previous": "이전 풀 도메인 주소",
            "results": [
            {
                "pk": 1,
                "poster": "이미지 주소",
                "title": "이씨 모임"
            }
            ]
        }""".trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramaInfoResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.count, 2)
        Assert.assertEquals(fullData.next, "다음 풀 도메인 주소")
        Assert.assertEquals(fullData.previous, "이전 풀 도메인 주소")
        Assert.assertEquals(fullData.results?.get(0)?.pk, 1)
        Assert.assertEquals(fullData.results?.get(0)?.poster, "이미지 주소")
        Assert.assertEquals(fullData.results?.get(0)?.title, "이씨 모임")

        // GIVEN2 : 문자열화된 json 데이터 (데이터가 없는 경우)
        val emptyJsonData = """{
            "count": 0,
            "next": null,
            "previous": null,
            "results": []
        }""".trimIndent().asJson

        // WHEN2 : 데이터가 없는 경우의 문자열 json 데이터를 파싱한다.
        val emptyData = DramaInfoResponse.serializer().parse(emptyJsonData)

        // THEN2 : 빈 경우에 대한 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(emptyData.count, 0)
        Assert.assertEquals(emptyData.next, null)
        Assert.assertEquals(emptyData.previous, null)
        Assert.assertEquals(emptyData.results?.size, 0)
    }

    /** 간략한 드라마 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasInfoPkTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """{
            "pk": 1,
            "poster": "이미지 주소",
            "title": "소녀의 세계",
            "avg_rating": 5,
            "summary": "이씨들이 모여서 발생하는 사건 사고들",
            "tag": [
                "멜로",
                "액션",
                "스릴러"
            ],
            "cast": [
                "이배우",
                "고배우",
                "삼배우"
            ],
            "director": "이감독",
            "dramas": [
            {
                "pk": "drama_id",
                "video_id": "PsTWEzt1GTw",
                "title": "엽떡을 먹는 방법 ",
                "episode": 1,
                "summary": "더미값더미값",
                "duration": 1,
                "small_thumbnail": "더미값더미값"
            }
            ]
        }""".trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramaInfoPkResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.pk, 1)
        Assert.assertEquals(fullData.poster, "이미지 주소")
        Assert.assertEquals(fullData.title, "소녀의 세계")
        Assert.assertEquals(fullData.avgRating, 5)
        Assert.assertEquals(fullData.summary, "이씨들이 모여서 발생하는 사건 사고들")
        Assert.assertEquals(fullData.tag?.get(0), "멜로")
        Assert.assertEquals(fullData.cast?.get(1), "고배우")
        Assert.assertEquals(fullData.director, "이감독")
        Assert.assertEquals(fullData.dramas?.get(0)?.summary, "더미값더미값")

//        // GIVEN2 : 문자열화된 json 데이터 (데이터가 없는 경우)
//        val emptyJsonData = """{
//        }""".trimIndent().asJson
//
//        // WHEN2 : 데이터가 없는 경우의 문자열 json 데이터를 파싱한다.
//        val emptyData = DramaInfoResponse.serializer().parse(emptyJsonData)
//
//        // THEN2 : 빈 경우에 대한 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
//        Assert.assertEquals(emptyData.count, 0)
//        Assert.assertEquals(emptyData.next, null)
//        Assert.assertEquals(emptyData.previous, null)
//        Assert.assertEquals(emptyData.results?.size, 0)
    }

    /** 드라마 검색 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasInfoSearchTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonArray = """[
            {
                "id": 2,
                "title": "소녀의 세계",
                "poster": "http://www.naver.com"
            }
        ]""".trimIndent().asJsonArray

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramaInfoSearchResponseItem.serializer().list.parseArray(fullJsonArray)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData[0].id, 2)
        Assert.assertEquals(fullData[0].title, "소녀의 세계")
        Assert.assertEquals(fullData[0].poster, "http://www.naver.com")

        // GIVEN2 : 문자열화된 json 데이터 (데이터가 없는 경우)
        val emptyJsonObject = """[
        ]""".trimIndent().asJsonArray

        // WHEN2 : 데이터가 없는 경우의 문자열 json 데이터를 파싱한다.
        val emptyData = DramaInfoSearchResponseItem.serializer().list.parseArray(emptyJsonObject)

        // THEN2 : 빈 경우에 대한 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(emptyData.size, 0)
    }
}
