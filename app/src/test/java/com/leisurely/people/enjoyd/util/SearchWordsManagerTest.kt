package com.leisurely.people.enjoyd.util

import com.leisurely.people.enjoyd.AndroidBaseTest
import com.leisurely.people.enjoyd.model.search.RecentSearch
import com.leisurely.people.enjoyd.data.local.prefs.SearchWordsManager
import org.junit.Assert
import org.junit.Test

/**
 * RecentSearchProvider.kt 테스트
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.20
 */
class SearchWordsManagerTest : AndroidBaseTest() {
    /** 맨 초기의 값을 가져오는 테스트 */
    @Test
    fun getInitValueTest() {
        val recents = SearchWordsManager.init()
        println("getInitValueTest init : $recents")
        Assert.assertEquals(recents.size, 0)

        // 모든 데이터를 clear 해준다.
        SearchWordsManager.deleteAll()
    }

    /** 값을 직접 넣어보는 테스트 */
    @Test
    fun putTest() {
        // GIVEN1, WHEN1 : 초기 데이터를 설정한다.
        var searchWords = SearchWordsManager.init()

        println("putTest init : ${searchWords.size}")
        // THEN1 : 맨 처음에 데이터는 0 이어야 한다.
        Assert.assertEquals(searchWords.size, 0)

        // GIVEN2, WHEN2 : 0L ~ 11L 까지의 데이터를 넣는다 (총 12개)
        SearchWordsManager.put(RecentSearch(id = 0L, title = "test1"))
        SearchWordsManager.put(RecentSearch(id = 1L, title = "test2"))
        SearchWordsManager.put(RecentSearch(id = 2L, title = "test3"))
        SearchWordsManager.put(RecentSearch(id = 3L, title = "test4"))
        SearchWordsManager.put(RecentSearch(id = 4L, title = "test5"))
        SearchWordsManager.put(RecentSearch(id = 5L, title = "test6"))
        SearchWordsManager.put(RecentSearch(id = 6L, title = "test7"))
        SearchWordsManager.put(RecentSearch(id = 7L, title = "test8"))
        SearchWordsManager.put(RecentSearch(id = 8L, title = "test9"))
        SearchWordsManager.put(RecentSearch(id = 9L, title = "test10"))
        SearchWordsManager.put(RecentSearch(id = 10L, title = "test11"))
        searchWords = SearchWordsManager.put(RecentSearch(id = 11L, title = "test12"))

        // THEN2-1 : 데이터의 총 개수는 10 이어야 한다
        println("putTest add1 : $searchWords")
        Assert.assertEquals(searchWords.size, 10)
        // THEN2-2 : 가장 최신 데이터는 마지막에 추가된 데이터여야 한다.
        Assert.assertEquals(searchWords[0].id, 11L)
        Assert.assertEquals(searchWords[0].title, "test12")

        // GIVEN3, WHEN3 : 5L 데이터를 넣는다.
        searchWords = SearchWordsManager.put(RecentSearch(id = 5L, title = "test6"))
        println("putTest add2 : $searchWords")
        // THEN3-1 : 데이터의 총 개수는 10 이어야 한다
        Assert.assertEquals(searchWords.size, 10)
        // THEN3-2 : 가장 최신 데이터는 마지막에 추가된 데이터여야 한다.
        Assert.assertEquals(searchWords[0].id, 5L)
        Assert.assertEquals(searchWords[0].title, "test6")
        // THEN3-3 : 이전 데이터 대신, 그 다음값이 앞으로 당겨와져야 한다.
        Assert.assertEquals(searchWords[6].id, 6L)
        Assert.assertEquals(searchWords[6].title, "test7")

        // GIVEN4, WHEN4 : 12L 데이터를 넣는다.
        searchWords = SearchWordsManager.put(RecentSearch(id = 12L, title = "test13"))
        println("putTest add3 : $searchWords")
        // THEN4-1 : 데이터의 총 개수는 10 이어야 한다
        Assert.assertEquals(searchWords.size, 10)
        // THEN4-2 : 가장 최신 데이터는 마지막에 추가된 데이터여야 한다.
        Assert.assertEquals(searchWords[0].id, 12L)
        Assert.assertEquals(searchWords[0].title, "test13")
        // THEN4-3 : 총 13개 데이터가 추가된 상태이므로, 가장 마지막 데이터 정보가 2L 이 아닌 3L 이어야 한다.
        Assert.assertEquals(searchWords[9].id, 3L)
        Assert.assertEquals(searchWords[9].title, "test4")

        // 모든 데이터를 clear 해준다.
        SearchWordsManager.deleteAll()
    }

    /** 값을 직접 삭제하는 테스트 */
    @Test
    fun deleteTest() {
        // GIVEN1, WHEN1 : 초기 데이터를 설정한다.
        var searchWords = SearchWordsManager.init()
        println("deleteTest init : $searchWords")
        // THEN1 : 맨 처음에 데이터는 0 이어야 한다.
        Assert.assertEquals(searchWords.size, 0)

        // GIVEN2 : 0L ~ 4L 데이터를 넣는다.
        SearchWordsManager.put(RecentSearch(id = 0L, title = "test1"))
        SearchWordsManager.put(RecentSearch(id = 1L, title = "test2"))
        SearchWordsManager.put(RecentSearch(id = 2L, title = "test3"))
        SearchWordsManager.put(RecentSearch(id = 3L, title = "test4"))
        SearchWordsManager.put(RecentSearch(id = 4L, title = "test5"))

        // WHEN2 : 2L 데이터를 삭제한다.
        searchWords = SearchWordsManager.delete("test3")
        println("deleteTest delete : $searchWords")

        // THEN2-1 : 데이터의 총 개수는 4 이어야 한다
        Assert.assertEquals(searchWords.size, 4)
        // THEN2-2 : 삭제된 데이터 위치에 그 다음값이 앞으로 당겨와져야 한다.
        Assert.assertEquals(searchWords[2].id, 1L)
        Assert.assertEquals(searchWords[2].title, "test2")

        // 모든 데이터를 clear 해준다.
        SearchWordsManager.deleteAll()
    }

}