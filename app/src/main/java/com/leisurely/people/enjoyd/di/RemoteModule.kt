package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.data.remote.source.AccountRemoteDataSource
import com.leisurely.people.enjoyd.data.remote.source.evaluation.DramaEvaluationDataSource
import com.leisurely.people.enjoyd.data.remote.source.drama.DramaSearchRemoteDataSource
import org.koin.dsl.module

/**
 * Remote DataSource 관련 DI 설정 하는 곳
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

val remoteModule = module {
    single { AccountRemoteDataSource(get()) }
    single { DramaEvaluationDataSource(get()) }
    single { DramaSearchRemoteDataSource(get()) }
}