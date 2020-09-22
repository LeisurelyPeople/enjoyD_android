package com.leisurely.people.enjoyd.data.local.prefs

import com.leisurely.people.enjoyd.model.search.RecentSearch
import com.leisurely.people.enjoyd.ui.base.EnjoyDApplication.Companion.appContext
import com.leisurely.people.enjoyd.util.Constant.Companion.PREF_RECENT_SEARCH_WORD
import com.leisurely.people.enjoyd.util.Serializer.asJsonArray
import com.leisurely.people.enjoyd.util.Serializer.parseArray
import com.leisurely.people.enjoyd.util.ext.getSharedPreference
import com.leisurely.people.enjoyd.util.ext.putSharedPreference
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json

object SearchWordsManager {
    private var recents = mutableListOf<RecentSearch>()

    fun init(): List<RecentSearch> {
        return try {
            recents = mutableListOf()
            val recentsJsonArray = appContext.getSharedPreference(
                PREF_RECENT_SEARCH_WORD, "[]"
            ).asJsonArray

            recents.addAll(RecentSearch.serializer().list.parseArray(recentsJsonArray))
            recents
        } catch (t: Throwable) {
            t.printStackTrace()
            recents
        }
    }

    /** 최근 검색 데이터를 추가한다. */
    fun put(recentSearch: RecentSearch): List<RecentSearch> {
        // 이전에 저장된 적이 있다면 그 값을 필터링해서 지워주고, 새롭게 저장될 값을 넣는다.
        recents = recents.filter {
            it.title != recentSearch.title
        } as MutableList<RecentSearch>
        recents.add(0, recentSearch)

        val newRecents = mutableListOf<RecentSearch>().apply { addAll(recents.take(10)) }
        recents = newRecents

        // SharedPreference 내에 저장한다.
        val recentJsonArrayStr = Json.stringify(RecentSearch.serializer().list, newRecents)
        appContext.putSharedPreference(PREF_RECENT_SEARCH_WORD, recentJsonArrayStr)

        return newRecents
    }

    /** 특정 데이터를 삭제한다. */
    fun delete(title: String): List<RecentSearch> {
        // 특정 데이터를 삭제한다.
        recents = recents.filter { it.title != title } as MutableList<RecentSearch>

        val newRecents = mutableListOf<RecentSearch>().apply { addAll(recents.take(10)) }
        recents = newRecents

        // SharedPreference 내에 변동 내역을 저장한다.
        val recentJsonArrayStr = Json.stringify(RecentSearch.serializer().list, newRecents)
        appContext.putSharedPreference(PREF_RECENT_SEARCH_WORD, recentJsonArrayStr)

        return newRecents
    }

    /** 모든 데이터를 삭제한다. */
    fun deleteAll(): List<RecentSearch> {
        // 특정 데이터를 삭제한다.
        recents.clear()

        // SharedPreference 내에 변동 내역을 저장한다.
        val recentJsonArrayStr = Json.stringify(RecentSearch.serializer().list,
            recents
        )
        appContext.putSharedPreference(PREF_RECENT_SEARCH_WORD, recentJsonArrayStr)

        return listOf()
    }
}