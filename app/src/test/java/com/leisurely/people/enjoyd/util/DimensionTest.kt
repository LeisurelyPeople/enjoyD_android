package com.leisurely.people.enjoyd.util

import com.leisurely.people.enjoyd.AndroidBaseTest
import com.leisurely.people.enjoyd.util.time.Dimension
import com.leisurely.people.enjoyd.util.time.dp
import com.leisurely.people.enjoyd.util.time.px
import com.leisurely.people.enjoyd.util.time.sp
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

/**
 * Dimension.kt 테스트
 *
 * @author ricky
 * @since v11.2.0 / 2020.06.26
 */

class DimensionTest : AndroidBaseTest() {
    /** 단위 변환 테스트 */
    @Test
    fun testConverting() {
        // assertEquals(15.sp, 30.px) : 이런 류의 테스트는 context 로 인해 체크 불가능
        assertEquals(10.dp, Dimension(10.dp).asDp)
        assertEquals(Dimension(23.dp).asSp, 23.dp.sp)
        assertEquals(Dimension(20.dp).asPx, 20.dp.px)
    }

    /** 더하기/빼기 테스트 */
    @Test
    fun testPlusMinus() {
        assertEquals(30.dp, 10.dp + 20.dp)
        assertEquals(-10.dp, 10.dp - 20.dp)
        // 가급적 소수점 연산은 진행하지 말 것
        // assertEquals(0.24.sp, -10.24.sp + 10.48.sp)

        assertEquals(Dimension(17.dp), Dimension(20.dp) - Dimension(3.dp))
        assertEquals(Dimension(23.dp), Dimension(20.dp) + Dimension(3.dp))
        Assert.assertEquals(75.3.toInt(), (Dimension(87.65.dp) - Dimension(12.35.dp)).asPx.toInt())
    }

    /** 곱하기 테스트 */
    @Test
    fun testMultiplying() {
        assertEquals(30.px, 15.px * 2)
        assertEquals(82.px, 20.5.px * 4)
        assertEquals(43.2.dp, (5.4.dp * 8))
        assertEquals(7.5.dp, (5.dp * 1.5).toFloat())

        assertEquals(Dimension(60.dp), Dimension(20.dp) * 3)
        assertEquals(Dimension(101.dp), Dimension(20.2.dp) * 5)
    }

    /** 나누기 테스트 */
    @Test
    fun testDividing() {
        assertEquals(4.px, 40.px / 10)
        assertEquals(2.5.px, 40.px / 16)
        assertEquals(6.dp, (15.dp / 2.5).toFloat())
        assertEquals(6.2.dp, (15.5.dp / 2.5).toFloat())

        assertEquals(Dimension(3.1.dp), Dimension(15.5.dp) / 5)
        assertEquals(Dimension(20.2.dp), Dimension(101.dp) / 5)
    }
}
