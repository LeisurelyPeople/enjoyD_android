package com.leisurely.people.enjoyd.model.enums

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

/**
 * API Service 들을 Koin 에서 구분하기 위한 enum class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.08
 */
enum class RetrofitQualifiers : Qualifier {
    AUTH,
    ENJOYD;

    override val value: QualifierValue
        get() = when (this) {
            AUTH -> "auth"
            ENJOYD -> "enjoyD"
        }
}