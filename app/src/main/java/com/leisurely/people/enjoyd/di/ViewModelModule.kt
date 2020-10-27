package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.ui.evaluation.EvaluationViewModel
import com.leisurely.people.enjoyd.ui.login.LoginViewModel
import com.leisurely.people.enjoyd.model.login.SocialLoginModel
import com.leisurely.people.enjoyd.ui.detail.DetailViewModel
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.ui.main.MainViewModel
import com.leisurely.people.enjoyd.ui.onboarding.SummaryEvaluationViewModel
import com.leisurely.people.enjoyd.ui.onboarding.UserInfoInputViewModel
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.ui.splash.SplashViewModel
import com.leisurely.people.enjoyd.ui.video.VideoViewModel
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
    viewModel { EvaluationViewModel() }
    viewModel { SearchViewModel(get()) }
    viewModel { (kakaoLogin: KakaoLogin) ->
        LoginViewModel(kakaoLogin, get())
    }
    viewModel { (socialLoginModel: SocialLoginModel) ->
        UserInfoInputViewModel(socialLoginModel, get())
    }
    viewModel { SummaryEvaluationViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { VideoViewModel() }
}
