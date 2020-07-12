package com.leisurely.people.enjoyd.util.TextDecoration

import android.graphics.Color
import android.text.TextUtils

/**
 * 색상에 대한 유틸 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
object ColorUtil {
    private val TAG = ColorUtil::class.java.canonicalName

    /**
     * 6자리 16진수 형식의 값("#FF00FF", "FF00FF" 등)을 int 형으로 변환.
     * setBackgroundColor() 에 값을 넣으면 됨
     *
     * @param bgColor 변환할 스트링 값
     */
    fun getColorStringToInt(bgColor: String?): Int {
        return getColorStringToInt(bgColor, "#ffffffff")
    }

    /**
     * 6자리 16진수 형식의 값("#FF00FF", "FF00FF" 등)을 int 형으로 변환.
     * setBackgroundColor() 에 값을 넣으면 됨
     *
     * @param bgColor   변환할 스트링 값
     * @param defaultBg 기본 값 (null = white)
     */
    fun getColorStringToInt(bgColor: String?, defaultBg: String?): Int {
        var bgColor = bgColor
        var defaultBg = defaultBg
        val color: Int
        if (TextUtils.isEmpty(defaultBg)) {
            defaultBg = "#ffffffff"
        }
        if (TextUtils.isEmpty(bgColor)) {
            bgColor = defaultBg
        }
        if (!bgColor!!.startsWith("#")) {
            bgColor = "#$bgColor"
        }
        if (bgColor.contains(" ")) {
            bgColor = bgColor.replace(" ".toRegex(), "")
        }
        color = try {
            Color.parseColor(bgColor)
        } catch (e: Exception) {
            // 더 구체적인 정보가 예외 메시지에 담기게 한다.
            val msg = """
                잘못된 color 문자열
                bgColor = "$bgColor"
                defaultBg = "$defaultBg"
                """.trimIndent()
            e.printStackTrace()

            Color.parseColor(defaultBg)
        }
        return color
    }
}
