package com.leisurely.people.enjoyd.util

import com.leisurely.people.enjoyd.util.time.*
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test
import java.util.Calendar.*

/**
 * DateUtil 클래스 테스트
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */
class DateUtilTest {
    /** 시간점의 각 프로퍼티를 제대로 처리하는지 테스트 */
    @Test
    fun timePointValue() {
        val timePoint = TimePoint.parse("2019-06-13 10:42:24", "yyyy-MM-dd HH:mm:ss")
        Assert.assertEquals(2019, timePoint.year)
        Assert.assertEquals(6, timePoint.month)
        Assert.assertEquals(13, timePoint.day)
        Assert.assertEquals(10, timePoint.hour)
        Assert.assertEquals(42, timePoint.minute)
        Assert.assertEquals(24, timePoint.second)
        // Assert.assertEquals(1560390144000, timePoint.unixMillis)
        // Assert.assertEquals(timePoint, TimePoint.fromUnixMillis(1560390144000))
    }

    /** 현재 시각 가져오기 테스트 */
    @Test
    fun timePointNow() {
        val timePoint = TimePoint.now
        val calendar = getInstance()

        Assert.assertEquals(calendar[YEAR], timePoint.year)
        Assert.assertEquals(calendar[MONTH] + 1, timePoint.month)
        Assert.assertEquals(calendar[DAY_OF_MONTH], timePoint.day)
        Assert.assertEquals(calendar[HOUR_OF_DAY], timePoint.hour)
        Assert.assertEquals(calendar[MINUTE], timePoint.minute)
    }

    /** 오늘 날짜 가져오기 테스트 */
    @Test
    fun timePointToday() {
        val timePoint = TimePoint.today
        val calendar = getInstance()

        Assert.assertEquals(calendar[YEAR], timePoint.year)
        Assert.assertEquals(calendar[MONTH] + 1, timePoint.month)
        Assert.assertEquals(calendar[DAY_OF_MONTH], timePoint.day)
        Assert.assertEquals(calendar[DAY_OF_MONTH], timePoint.day)
        Assert.assertEquals(0, timePoint.hour)
        Assert.assertEquals(0, timePoint.minute)
        Assert.assertEquals(0, timePoint.second)
    }

    /** "yyyyMMddhhmmss" 문자열 파싱 테스트 */
    @Test
    fun timePointFromyyyyMMddhhmmss() {
        // 년월일시분초가 모두 주어졌을 때
        TimePoint(year = 2018, month = 9, day = 5, hour = 15, minute = 23, second = 49).run {
            Assert.assertEquals(2018, year)
            Assert.assertEquals(9, month)
            Assert.assertEquals(5, day)
            Assert.assertEquals(15, hour)
            Assert.assertEquals(23, minute)
            Assert.assertEquals(49, second)
        }

        // 년월일시분만 주어졌을 때
        TimePoint.fromDateTimeString("201809051526").run {
            Assert.assertEquals(2018, year)
            Assert.assertEquals(9, month)
            Assert.assertEquals(5, day)
            Assert.assertEquals(15, hour)
            Assert.assertEquals(26, minute)
            Assert.assertEquals(0, second)
        }

        // 년월일만 주어졌을 때
        TimePoint.fromDateTimeString("20180905").run {
            Assert.assertEquals(2018, year)
            Assert.assertEquals(9, month)
            Assert.assertEquals(5, day)
            Assert.assertEquals(0, hour)
            Assert.assertEquals(0, minute)
            Assert.assertEquals(0, second)
        }
    }

    /** 연산자 테스트 */
    @Test
    fun operator() {
        // 시간량 간 더하기
        Assert.assertEquals("345600000 밀리초", "${2.days + 48.hours}")

        // 시간량 간 사칙연산
        assertEquals(10.hours + 30.minutes, 12.hours - 30.minutes * 3)

        // 시간점에서 시간량 빼기/더하기
        Assert.assertEquals(
            "2018-09-05 14:35:32",
            "${TimePoint(2018, 9, 5, hour = 15) - 25.minutes + 32.seconds}"
        )

        // 두 시간점의 차 구하기
        assertEquals(6.days, TimePoint(2018, 9, 2) - TimePoint(2018, 8, 27))

        // 두 시간점 비교
        assertEquals(TimePoint.fromDateTimeString("20180509"), TimePoint(2018, 5, 9))

        // 음수 시간량 및 마이너스 부호 연산자 테스트
        assertEquals(-(3.hours), 3.hours - 6.hours)

        // 시간량 대소 비교
        assert(3.hours + 15.minutes < 4.hours - 15.minutes)

        // 시간점 대소 비교
        assert(TimePoint(2018, 5, 30) > TimePoint(2017, 10, 23))

        // 시간점 구간 포함 여부
        val start = TimePoint(2018, 7, 5)
        val end = TimePoint(2018, 8, 2)
        assert(TimePoint(2018, 5, 30) !in start..end)
        assert(TimePoint(2018, 7, 23) in start..end)
        assert(TimePoint(2018, 9, 5) !in start..end)
    }

    /** [TimeAmount] 확장 함수 테스트 */
    @Test
    fun timeAmountExtension() {
        assertEquals(24.hours, 1.days)
        assertEquals(60.minutes, 1.hours)
        assertEquals(60.seconds, 1.minutes)
        assertEquals(1000.millis, 1.seconds)
        assertEquals(TimeAmount(asMillis = 1), 1.millis)
    }

    /** [TimeAmount] 포매팅 테스트 */
    @Test
    fun timeAmountFormat() {
        Assert.assertEquals("07:08", (7.minutes + 8.seconds).format("mm:ss"))
        Assert.assertEquals("07:08", (7.hours + 8.minutes).format("HH:mm"))
        Assert.assertEquals("23:19:04", (22.hours + 78.minutes + 64.seconds).format("HH:mm:ss"))
        Assert.assertEquals("231904", (22.hours + 78.minutes + 64.seconds).format("HHmmss"))
        Assert.assertEquals("3일 11:09", (2.days + 35.hours + 9.minutes).format("D일 HH:mm"))
        Assert.assertEquals(
            "03일 11:09:07",
            (2.days + 35.hours + 9.minutes + 7.seconds).format("DD일 HH:mm:ss")
        )
        Assert.assertEquals(
            "1일 13시간 5분 1초 전",
            (1.days + 13.hours + 5.minutes + 1.seconds).format("D일 H시간 m분 s초 전")
        )
    }

    /** 큰 [TimeAmount]를 더할 때 오버플로 일어나는지 테스트 */
    @Test
    fun overflow() {
        assertEquals(TimePoint(2019, 5, 31), TimePoint(2019, 5, 1) + 30.days)
        assertEquals(
            TimePoint(2019, 1, 1) + 365.millis,
            TimePoint(2018, 1, 1) + 365.days + 365.millis
        )
    }
}
