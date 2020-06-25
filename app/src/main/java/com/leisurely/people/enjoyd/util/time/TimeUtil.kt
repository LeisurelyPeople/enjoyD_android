package com.leisurely.people.enjoyd.util.time

import androidx.annotation.StringRes
import com.leisurely.people.enjoyd.util.coroutine.appContext
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.internal.StringDescriptor
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜 및 시간을 관리하는 유틸 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */

/** "yyyyMMddhhmmss" 형식의 문자열로부터 시간점을 생성하는 serializer */
object DateTimeStringSerializer : KSerializer<TimePoint> {
    override val descriptor: SerialDescriptor = StringDescriptor

    /** string -> timePoint */
    override fun deserialize(decoder: Decoder): TimePoint {
        val string = decoder.decodeString()
        return TimePoint.fromDateTimeString(string)
    }

    /** timePoint -> string */
    override fun serialize(encoder: Encoder, value: TimePoint) {
        encoder.encodeString(value.format("yyyyMMddHHmmss"))
    }
}

/**
 * 시간점 (시각)
 *
 * 시간의 한 지점을 나타냄
 */
class TimePoint private constructor(
    private val calendar: Calendar
) : Comparable<TimePoint>, Serializable {
    /** 지정한 값으로 시간점을 생성한다. */
    constructor(year: Int, month: Int, day: Int, hour: Int = 0, minute: Int = 0, second: Int = 0)
            : this(GregorianCalendar(year, month - 1, day, hour, minute, second))

    val year: Int get() = calendar[Calendar.YEAR]
    val month: Int get() = calendar[Calendar.MONTH] + 1
    val day: Int get() = calendar[Calendar.DAY_OF_MONTH]
    val hour: Int get() = calendar[Calendar.HOUR_OF_DAY]
    val minute: Int get() = calendar[Calendar.MINUTE]
    val second: Int get() = calendar[Calendar.SECOND]

    /** 년·월·일만 갖는 시간점 */
    val onlyDate get() = TimePoint(year, month, day)

    /** 시·분·초만 갖는 시간점 (날짜는 오늘 날짜) */
    val onlyTime get() = TimePoint(now.year, now.month, now.day, hour, minute, second)

    /** 밀리초 단위의 유닉스 타임스탬프 */
    val unixMillis: Long get() = this.calendar.timeInMillis

    /** [amount] 후의 시간점을 반환한다. */
    operator fun plus(amount: TimeAmount): TimePoint {
        val copy: Calendar = calendar.clone() as Calendar
        copy.add(Calendar.SECOND, amount.asSeconds)
        copy.add(Calendar.MILLISECOND, (amount.asMillis % 1000L).toInt())
        return TimePoint(copy)
    }

    /** [amount] 이전의 시간점을 반환한다. */
    operator fun minus(amount: TimeAmount): TimePoint = plus(-amount)

    /** this 시간점에서 [other] 시간점만큼 뺀 시간량을 반환한다. */
    operator fun minus(other: TimePoint) =
        TimeAmount(this.calendar.timeInMillis - other.calendar.timeInMillis)

    /** 대소 비교 */
    override operator fun compareTo(other: TimePoint): Int {
        return this.calendar.compareTo(other.calendar)
    }

    /** 비교 */
    override fun equals(other: Any?) = this.calendar == (other as? TimePoint)?.calendar

    override fun hashCode() = calendar.hashCode()

    override fun toString() =
        "%04d-%02d-%02d %02d:%02d:%02d".format(year, month, day, hour, minute, second)

    /**
     * [pattern] 포맷대로 시간점을 문자열로 변환한다. 포맷 문자는
     * [이 문서](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) 참조
     * @return 포맷에 맞추어진 값 리턴 (ex. 20200204)
     * @return
     */
    fun format(@StringRes pattern: Int): String = format(appContext.getString(pattern))

    /** @see format */
    fun format(pattern: String): String =
        SimpleDateFormat(pattern, Locale.KOREAN).format(calendar.time)

    companion object {
        /** 현재 시각 반환 */
        val now
            get() = TimePoint(Calendar.getInstance())

        /** 오늘 날짜 반환 (시·분·초 = 0) */
        val today
            get() = TimePoint.now.onlyDate

        /** 오늘의 시간값만 반환 */
        val time
            get() = TimePoint.now.onlyTime

        /**
         * [pattern] 포맷대로 [문자열][source]을 시간점으로 변환한다. 포맷 문자는
         * [이 문서](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) 참조
         *
         * @param pattern 포맷 문자열이 담긴 문자열 리소스 (ex. 20200204)
         */
        fun parse(source: String, @StringRes pattern: Int): TimePoint =
            parse(source, appContext.getString(pattern))

        /** @see parse */
        fun parse(source: String, pattern: String): TimePoint {
            val date: Date = SimpleDateFormat(pattern, Locale.KOREAN)
                .apply { isLenient = true }
                .parse(source)
            return TimePoint(GregorianCalendar().apply { time = date })
        }

        /** 밀리초 단위의 유닉스 타임스탬프로부터 시간점을 생성한다. */
        fun fromUnixMillis(timestamp: Long): TimePoint {
            return this.now.apply {
                this.calendar.timeInMillis = timestamp
            }
        }

        /**
         * "yyyyMMddhhmmss" 형식의 문자열로부터 시간점을 생성한다.
         *
         * @param dateTime 끝에서부터 `ss`, `mm`, `hh`, `dd`, `MM` 자리는 생략 가능.
         *                 생략된 부분은 0으로 초기화 된다.
         */
        fun fromDateTimeString(dateTime: String): TimePoint {
            val year: Int = dateTime[0..3]
            val month: Int = dateTime[4..5]
            val day: Int = dateTime[6..7]
            val hour: Int = dateTime[8..9]
            val minute: Int = dateTime[10..11]
            val second: Int = dateTime[12..13]

            return TimePoint(GregorianCalendar(year, month - 1, day, hour, minute, second))
        }

        /** fromDateTimeString 에서 Int 값을 보다 원활하게 뽑아온다. */
        private operator fun String.get(range: IntRange): Int = try {
            this.substring(range).toInt()
        } catch (ignored: Throwable) {
            0
        }
    }
}

/**
 * 시간량 (시간)
 *
 * 두 [시간점][TimePoint]의 차이값을 나타낸다.
 *
 * @property asMillis 시간의 양을 나타내는 밀리초
 */
class TimeAmount(val asMillis: Long) : Comparable<TimeAmount> {
    /** 시간량을 일 단위로 환산한 값 (소수점 제외) */
    val asDays: Int get() = asHours / 24

    /** 시간량을 시 단위로 환산한 값 (소수점 제외) */
    val asHours: Int get() = asMinutes / 60

    /** 시간량을 분 단위로 환산한 값 (소수점 제외) */
    val asMinutes: Int get() = asSeconds / 60

    /** 시간량을 초 단위로 환산한 값 (소수점 제외) */
    val asSeconds: Int get() = (asMillis / 1000).toInt()

    /** 두 시간량을 더한 시간량을 반환한다. */
    operator fun plus(other: TimeAmount) = TimeAmount(this.asMillis + other.asMillis)

    /** this 시간량에서 [other] 시간량을 뺀 시간량을 반환한다. */
    operator fun minus(other: TimeAmount) = TimeAmount(this.asMillis - other.asMillis)

    /** this 시간량에서 [times] 만큼 곱한 시간량을 반환한다. */
    operator fun times(times: Int) = TimeAmount(this.asMillis * times)

    /** 부호 반전 */
    operator fun unaryMinus() = TimeAmount(-this.asMillis)

    /** 대소 비교 */
    override operator fun compareTo(other: TimeAmount) = this.asMillis.compareTo(other.asMillis)

    /** 비교 */
    override fun equals(other: Any?) = this.asMillis == (other as? TimeAmount)?.asMillis

    override fun hashCode() = asMillis.hashCode()

    override fun toString() = "$asMillis 밀리초"

    /**
     * [pattern] 포맷대로 시간량을 문자열로 변환한다.
     * 포맷 문자는 `"D"`, `"HH"`, `"mm"`, `"ss"` 를 지원한다.
     *
     * [이 문서](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) 참조
     *
     * @param pattern 포맷 문자열이 담긴 문자열 리소스 (ex. yyyyMMdd)
     * @return 포맷에 맞추어진 값 리턴 (ex. 20200204)
     */
    fun format(@StringRes pattern: Int): String = format(appContext.getString(pattern))

    /** @see format */
    fun format(pattern: String): String {
        val day = this.asDays
        val hour = this.asHours % 24
        val minute = this.asMinutes % 60
        val second = this.asSeconds % 60

        return pattern
            .replace("DD", "%02d".format(day))
            .replace("HH", "%02d".format(hour))
            .replace("mm", "%02d".format(minute))
            .replace("ss", "%02d".format(second))
            .replace("D", "$day")
            .replace("H", "$hour")
            .replace("m", "$minute")
            .replace("s", "$second")
    }
}

/** 지정한 수만큼의 시간량(일) 반환 */
val Int.days get() = TimeAmount(asMillis = this.toLong() * 24 * 60 * 60 * 1000)

/** 지정한 수만큼의 시간량(시간) 반환 */
val Int.hours get() = TimeAmount(asMillis = this.toLong() * 60 * 60 * 1000)

/** 지정한 수만큼의 시간량(분) 반환 */
val Int.minutes get() = TimeAmount(asMillis = this.toLong() * 60 * 1000)

/** 지정한 수만큼의 시간량(초) 반환 */
val Int.seconds get() = TimeAmount(asMillis = this.toLong() * 1000)

/** 지정한 수만큼의 시간량(밀리초) 반환 */
val Int.millis get() = TimeAmount(asMillis = this.toLong())
