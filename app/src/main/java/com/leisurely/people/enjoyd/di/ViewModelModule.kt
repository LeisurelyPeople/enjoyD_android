package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.ui.login.LoginViewModel
import com.leisurely.people.enjoyd.model.login.SocialLoginModel
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.ui.main.MainViewModel
import com.leisurely.people.enjoyd.ui.main.evaluation.EvaluationViewModel
import com.leisurely.people.enjoyd.ui.main.home.HomeViewModel
import com.leisurely.people.enjoyd.ui.main.mypage.MyPageViewModel
import com.leisurely.people.enjoyd.ui.onboarding.SummaryEvaluationViewModel
import com.leisurely.people.enjoyd.ui.onboarding.UserInfoInputViewModel
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.ui.splash.SplashViewModel
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
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { EvaluationViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { (kakaoLogin: KakaoLogin) ->
        LoginViewModel(kakaoLogin, get())
    }
    viewModel { (socialLoginModel: SocialLoginModel) ->
        UserInfoInputViewModel(socialLoginModel, get())
    }
    viewModel { SummaryEvaluationViewModel(get()) }
}
