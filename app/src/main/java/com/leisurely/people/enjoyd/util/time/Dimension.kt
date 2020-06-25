package com.leisurely.people.enjoyd.util.time

import android.util.TypedValue
import com.leisurely.people.enjoyd.util.coroutine.appContext

/**
 * 길이·치수 관련 데이터를 관리 (dp sp px)
 *
 * @author ricky
 * @since v11.2.0 / 2020.03.15
 * @property rawValue 내부 저장용 값 (단위: px)
 *
 * px를 기준으로 operator 함수 생성
 */
class Dimension(private val rawValue: Number) {
    val asPx: Number get() = rawValue
    val asDp: Number get() = rawValue.dp
    val asSp: Number get() = rawValue.sp

    override fun equals(other: Any?) = this.asPx == (other as? Dimension)?.asPx

    override fun hashCode() = asPx.hashCode()

    /** this 를 [number]만큼 나눈 [길이][Dimension]를 반환한다. 소수점은 항상 보존된다. */
    operator fun div(number: Number) = Dimension(this.asPx.toFloat() / number.toFloat())

    /** this 에서 [other]을 뺀 [길이][Dimension]를 반환한다. */
    operator fun minus(other: Dimension) = Dimension(this.asPx.toFloat() - other.asPx.toFloat())

    /** 두 [길이][Dimension]를 더한 값을 반환한다. */
    operator fun plus(other: Dimension) = Dimension(this.asPx.toFloat() + other.asPx.toFloat())

    /** this 에 [number]만큼 곱한 값을 반환한다. */
    operator fun times(number: Number) = Dimension(this.asPx.toFloat() * number.toFloat())

    /** 부호를 반전한 [길이][Dimension]를 반환한다. */
    operator fun unaryMinus() = Dimension(-this.asPx.toFloat())
}

/** 실수형 dp 길이를 생성한다. */
val Number.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        appContext.resources.displayMetrics
    )

/** 정수형 dp 길이를 생성한다. */
val Number.dpInt: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        appContext.resources.displayMetrics
    ).toInt()

/** 실수형 px 길이를 생성한다. */
val Number.px: Float
    get() = this.toFloat()

/** 정수형 px 길이를 생성한다. */
val Number.pxInt: Int
    get() = this.toInt()

/** 실수형 sp 길이를 생성한다. */
val Number.sp: Float
    get() = (this.toFloat() * appContext.resources.displayMetrics.scaledDensity)

/** 정수형 sp 길이를 생성한다. */
val Number.spInt: Int
    get() = (this.toFloat() * appContext.resources.displayMetrics.scaledDensity).toInt()
