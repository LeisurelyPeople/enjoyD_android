package com.leisurely.people.enjoyd.ui.base

import android.app.Application
import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.di.networkModule
import com.leisurely.people.enjoyd.di.viewModelModule
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
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@EnjoyDApplication)
            modules(networkModule, viewModelModule)
        }
    }

    companion object {
        lateinit var instance: EnjoyDApplication
            private set
    }
}
