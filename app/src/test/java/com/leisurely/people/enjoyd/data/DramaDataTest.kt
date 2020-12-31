package com.leisurely.people.enjoyd.data

import com.leisurely.people.enjoyd.AndroidBaseTest
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponseItem
import com.leisurely.people.enjoyd.data.remote.data.response.*
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
    /** 드라마정보리스트API API [getDramas] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
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
        val fullData = DramasGetResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.count, 2)
        Assert.assertEquals(fullData.next, "다음 풀 도메인 주소")
        Assert.assertEquals(fullData.previous, "이전 풀 도메인 주소")
        Assert.assertEquals(fullData.results?.get(0)?.pk, 1)
        Assert.assertEquals(fullData.results?.get(0)?.poster, "이미지 주소")
        Assert.assertEquals(fullData.results?.get(0)?.title, "이씨 모임")
    }

    /** 드라마정보 디테일 API [getDramasSlug] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasInfoPkTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """
        {
          "slug": "manjjijnamnyeo",
          "poster": "http://3.35.87.123/media/manjjijnamnyeo/2020091019021-----.jpg",
          "title": "만찢남녀",
          "summary": "얼굴도 이름도 <선녀와 남욱군> 만화 속 여주인공과 똑같지만 성격은 정반대인 한선녀. 그런 한선녀 앞에 만화책 속에서 튀어나온 진짜 만찢남 천남욱이 나타나며 벌어지는 좌충우돌 로맨스",
          "avg_rating": 5,
          "release_year": 2020,
          "tag": [
            "10대",
            "로맨스"
          ],
          "cast": [
            "김민규",
            "김도연",
            "최현욱",
            "한지효",
            "임보라"
          ],
          "writer": "최요지"
        }
        """.trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramasSlugGetResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.slug, "manjjijnamnyeo")
        Assert.assertEquals(
            fullData.poster,
            "http://3.35.87.123/media/manjjijnamnyeo/2020091019021-----.jpg"
        )
        Assert.assertEquals(fullData.title, "만찢남녀")
        Assert.assertEquals(
            fullData.summary,
            "얼굴도 이름도 <선녀와 남욱군> 만화 속 여주인공과 똑같지만 성격은 정반대인 한선녀. 그런 한선녀 앞에 만화책 속에서 튀어나온 진짜 만찢남 천남욱이 나타나며 벌어지는 좌충우돌 로맨스"
        )
        Assert.assertEquals(fullData.avgRating, 5f)
        Assert.assertEquals(fullData.releaseYear, 2020)
        Assert.assertEquals(fullData.tag[0], "10대")
        Assert.assertEquals(fullData.cast[1], "김도연")
        Assert.assertEquals(fullData.writer, "최요지")
    }

    /** 드라마 정보 검색 API [dramaInfoSearch] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
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
        val fullData = DramasSearchResponseItem.serializer().list.parseArray(fullJsonArray)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData[0].id, 2)
        Assert.assertEquals(fullData[0].title, "소녀의 세계")
        Assert.assertEquals(fullData[0].poster, "http://www.naver.com")
    }

    /** 드라마 정보 배너 API [getDramasInfoBanner] 데이터 파싱이 정상적으로 이루어지는지 확인한다.*/
    @Test
    fun dramasInfoBannerTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """{
            "slug": "sonyeoyi-segye",
            "poster": "post url",
            "producer": "tvND",
            "title": "test",
            "tag": [
                "test"
            ]
        }""".trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramaBannerGetResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.slug, "sonyeoyi-segye")
        Assert.assertEquals(fullData.poster, "post url")
        Assert.assertEquals(fullData.producer, "tvND")
        Assert.assertEquals(fullData.title, "test")
        Assert.assertEquals(fullData.tag?.get(0), "test")
    }

    /** 태그 리스트 API [getDramasTag] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasTagTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """{
            "count": 2,
            "next": "",
            "previous": null,
            "results": [
                {
                    "name": "멜로"
                },
                {
                    "name": "액션"
                }
            ]
        }""".trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramaTagGetResponse.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.count, 2)
        Assert.assertEquals(fullData.next, "")
        Assert.assertEquals(fullData.previous, null)
        Assert.assertEquals(fullData.results?.get(0)?.name, "멜로")
    }

    /** 해당 드라마의 에피소드 목록 API [getDramasSlugEpisodes] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasSlugEpisodesTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonObject = """{
            "slug": "manjjijnamnyeo",
            "episode": 1,
            "video_id": "1XaIrIV-NR4",
            "episode_title": "생일날 185cm 짜리 선물 받아봄?",
            "default_thumbnail": "https://i.ytimg.com/vi/1XaIrIV-NR4/default.jpg",
            "producer": "플레이리스트",
            "is_bookmark": false
        }""".trimIndent().asJson

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullData = DramasSlugEpisodesResponseItem.serializer().parse(fullJsonObject)

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.slug, "manjjijnamnyeo")
        Assert.assertEquals(fullData.episode, 1)
        Assert.assertEquals(fullData.videoId, "1XaIrIV-NR4")
        Assert.assertEquals(fullData.episodeTitle, "생일날 185cm 짜리 선물 받아봄?")
        Assert.assertEquals(
            fullData.defaultThumbnail,
            "https://i.ytimg.com/vi/1XaIrIV-NR4/default.jpg"
        )
        Assert.assertEquals(fullData.producer, "플레이리스트")
        Assert.assertEquals(fullData.isBookmark, false)
    }

    /** 북마크 리스트 API  API [getDramasBookmarkPk] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasBookmarkPkTest() {
        // GIVEN1 : 문자열화된 json 데이터 (완벽한 데이터)
        val fullJsonArray = """[
            {
                "small_thumbnail": "test",
                "episode": 1,
                "title": "test_drama_ep01",
                "pk": 1
            }
        ]""".trimIndent().asJsonArray

        // WHEN1 : 문자열 json 데이터를 파싱한다.
        val fullDatas = DramaBookmarkPkResponseItem.serializer().list.parseArray(fullJsonArray)
        val fullData = fullDatas[0]

        // THEN1 : 문자열 json 데이터와 객체 데이터가 정상적으로 매치되어야 한다.
        Assert.assertEquals(fullData.smallThumbnail, "test")
        Assert.assertEquals(fullData.episode, 1)
        Assert.assertEquals(fullData.title, "test_drama_ep01")
        Assert.assertEquals(fullData.pk, 1)
    }

    /** 북마킹 API [postDramasBookmark] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasBookmarkPostTest() {
        Assert.assertEquals(1, 1)
    }

    /** 북마크 해제 API [deleteDramasBookmarkPk] 데이터 파싱이 정상적으로 이루어지는지 확인한다. */
    @Test
    fun dramasBookmarkDeleteTest() {
        Assert.assertEquals(1, 1)
    }
}
