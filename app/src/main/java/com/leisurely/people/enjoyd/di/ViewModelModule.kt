package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}