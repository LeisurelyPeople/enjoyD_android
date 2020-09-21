package com.leisurely.people.enjoyd

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.leisurely.people.enjoyd.data.remote.api.AuthService
import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.interceptor.AuthInterceptor
import com.leisurely.people.enjoyd.di.provideAuthService
import com.leisurely.people.enjoyd.di.provideDefaultOkHttpClient
import com.leisurely.people.enjoyd.di.provideEnjoyDService
import com.leisurely.people.enjoyd.di.provideOkHttpClient
import com.leisurely.people.enjoyd.model.enums.RetrofitQualifiers
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.annotation.Config

/**
 * Robolectric 을 사용하는 모든 테스트 클래스의 공통
 * 기본적으로 GIVEN WHEN THEN 순서로 테스트 코드를 작성한다.
 *
 * given 파트는 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분이다.
 * when  파트는 구체화하고자 하는 그 행동이 된다.
 * then  파트는 어떤 특정한 행동 때문에 발생할거라고 예상되는 변화에 대해 설명하는 부분이다.
 *
 * 참고 link : https://velog.io/@pop8682/%EB%B2%88%EC%97%AD-Given-When-Then-martin-fowler
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */
@RunWith(AndroidJUnit4::class)
@Config(application = Application::class)
abstract class AndroidBaseTest : BaseTest(), KoinTest {
    val authApi: EnjoyDService by inject()
    val noneAuthApi: AuthService by inject()

    val mockModule = module {
        single { AuthInterceptor(ApplicationProvider.getApplicationContext()) }
        single { provideEnjoyDService(get(qualifier = RetrofitQualifiers.ENJOYD)) }
        single { provideAuthService(get(qualifier = RetrofitQualifiers.AUTH)) }
        single(RetrofitQualifiers.ENJOYD) { provideOkHttpClient(get()) }
        single(RetrofitQualifiers.AUTH) { provideDefaultOkHttpClient() }
    }

    @Before
    fun setupContext() {
        startKoin { modules(mockModule) }
    }

    @After
    fun after() {
        stopKoin()
    }

}
