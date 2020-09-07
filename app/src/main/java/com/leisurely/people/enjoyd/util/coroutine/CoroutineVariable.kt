package com.leisurely.people.enjoyd.util.coroutine

import android.content.Context
import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.data.remote.api.APIService
import com.leisurely.people.enjoyd.di.provideApi
import com.leisurely.people.enjoyd.di.provideOkHttpClient
import com.leisurely.people.enjoyd.di.provideRetrofit

/**
 * coroutine 패키지 내의 파일이나, 코루틴 로직에서 사용하는 변수 모음
 * TODO 전역에서 쓰이는 파일들도 있으므로 논의를 통해 아래 변수들의 위치 조정이 필요함
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */

// 전역 context
lateinit var appContext: Context

// 앱 버전 정보
val appVersion: String by lazy { BuildConfig.VERSION_NAME }

// 지금 실행되는 코드가 Unit Test 인지 확인하는 flag
var isUnitTest = false
