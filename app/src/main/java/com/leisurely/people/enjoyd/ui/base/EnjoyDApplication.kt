package com.leisurely.people.enjoyd.ui.base

import android.app.Application
import android.content.Intent
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
    }
}
