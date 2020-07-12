package com.leisurely.people.enjoyd.util.Serializer

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*

/**
 * kotlinx-serialization 관련 유틸리티
 *
 * @author ricky
 * @since v11.4.0 / 2020.07.12
 */

/** 기본 JSON 파서 */
private val jsonParser = Json(JsonConfiguration(ignoreUnknownKeys = false))

/** [String]을 [JsonObject] 타입으로 변환한다. */
val String.asJson: JsonObject
    get() = try {
        jsonParser.parseJson(this.trimIndent()).jsonObject
    } catch (throwable: Throwable) {
        throw SerializationException("JsonObject 파싱 실패:\n$this", throwable)
    }

/** [String]을 [JsonArray] 타입으로 변환한다. */
val String.asJsonArray: JsonArray
    get() = try {
        jsonParser.parseJson(this.trimIndent()).jsonArray
    } catch (throwable: Throwable) {
        throw SerializationException("JsonArray 파싱 실패:\n$this", throwable)
    }

/** [Json.fromJson]에 대한 wrapper 함수 */
fun <T> DeserializationStrategy<T>.parse(json: JsonElement): T = try {
    jsonParser.fromJson(this, json)
} catch (throwable: Throwable) {
    throw SerializationException("JsonObject 파싱 실패:\n$json", throwable)
}

/** [Json.fromJson]에 대한 wrapper 함수 */
fun <T> DeserializationStrategy<T>.parseArray(json: JsonArray): T = try {
    jsonParser.fromJson(this, json)
} catch (throwable: Throwable) {
    throw SerializationException("JsonArray 파싱 실패:\n$json", throwable)
}
