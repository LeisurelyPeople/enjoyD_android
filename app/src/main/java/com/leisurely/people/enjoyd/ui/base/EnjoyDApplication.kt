package com.leisurely.people.enjoyd.ui.base

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.annotation.VisibleForTesting
import com.kakao.auth.KakaoSDK
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.di.*
import com.leisurely.people.enjoyd.ui.login.LoginActivity
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoSDKAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * EnjoyD Application class
 * application instance, koin setting
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

class EnjoyDApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = this

        KakaoSDK.init(KakaoSDKAdapter(this))

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@EnjoyDApplication)
            modules(
                networkModule,
                viewModelModule,
                remoteModule,
                localModule,
                repositoryModule,
                searchViewModule
            )
        }
    }

    fun logout() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
                TokenManager.deleteUserToken(this@EnjoyDApplication)
                val intent = LoginActivity.getIntent(this@EnjoyDApplication).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        })
    }

    companion object {
        lateinit var instance: EnjoyDApplication
            private set

        // Unit Test 의 정확성과 원활함을 더하기 위해 사용하는 context 변수
        lateinit var appContext: Context

        /**
         * Unit test 에서 사용할 application class 인스턴스 값과 context 를 설정한다.
         * 반드시 Unit Test 에서만 사용한다.
         */
        @VisibleForTesting
        fun injectUnitTestContext(instance: EnjoyDApplication, appContext: Context) {
            this.instance = instance
            this.appContext = appContext
        }
    }
}
