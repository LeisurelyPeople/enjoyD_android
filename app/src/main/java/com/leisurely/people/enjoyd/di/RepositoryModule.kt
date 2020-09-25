package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.data.repository.AccountRepository
import com.leisurely.people.enjoyd.data.repository.evaluation.DramaEvaluationRepository
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import org.koin.dsl.module

/**
 * Repository 관련 DI 설정 하는 곳
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

val repositoryModule = module {
    single { AccountRepository(get(), get()) }
    single { DramaEvaluationRepository(get()) }
    single { DramaRepository(get(), get()) }
}