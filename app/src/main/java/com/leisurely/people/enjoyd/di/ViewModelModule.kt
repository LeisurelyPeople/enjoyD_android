package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModel DI 설정을 위한 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** 뷰모델 모듈(DI) 설정 */
val viewModelModule = module {
    viewModel { MainViewModel() }
}