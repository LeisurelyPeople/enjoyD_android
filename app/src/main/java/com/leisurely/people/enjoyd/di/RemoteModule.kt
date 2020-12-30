package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.data.remote.source.*
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
    single { AccountRemoteDataSource(get(), get()) }
    single { DramaEvaluationDataSource(get()) }
    single { DramaSearchRemoteDataSource(get()) }
    single { DramasBannerRemoteDataSource(get()) }
    single { DramasTagRemoteDataSource(get()) }
    single { DramasWatchingRemoteDataSource(get()) }
    single { DramasBookmarkRemoteDataSource(get()) }
    single { QuestionRemoteDataSource(get()) }
}