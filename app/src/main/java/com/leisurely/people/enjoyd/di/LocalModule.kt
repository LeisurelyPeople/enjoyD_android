package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.data.local.source.AccountLocalDataSource
import com.leisurely.people.enjoyd.data.local.source.DramaLocalDataSource
import com.leisurely.people.enjoyd.data.remote.source.AccountRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Local DataSource 관련 DI 설정 하는 곳
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.25
 */

val localModule = module {
    single {
        AccountLocalDataSource(androidContext())
    }
    single {
        DramaLocalDataSource(androidContext())
    }
}