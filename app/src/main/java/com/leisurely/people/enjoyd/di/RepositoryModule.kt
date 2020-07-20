package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.data.repository.LoginRepository
import org.koin.dsl.module

/**
 * Repository 관련 DI 설정 하는 곳
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

val repositoryModule = module {
    single {
        LoginRepository(get())
    }
}